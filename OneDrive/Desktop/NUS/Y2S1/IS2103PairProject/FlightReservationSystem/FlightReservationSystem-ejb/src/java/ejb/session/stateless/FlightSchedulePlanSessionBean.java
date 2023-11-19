/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Fare;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.FlightDisabledException;
import util.exception.FlightSchedulePlanNotFoundException;
import util.exception.InputDataValidationException;

/**
 *
 * @author alvintjw
 */
@Stateless
public class FlightSchedulePlanSessionBean implements FlightSchedulePlanSessionBeanRemote, FlightSchedulePlanSessionBeanLocal {

    @EJB
    private FlightScheduleSessionBeanRemote flightScheduleSessionBean;
    
    @EJB
    private FlightSessionBeanLocal flightSessionBeanLocal;

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
     private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    
    
    public FlightSchedulePlanSessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public Long createCompliMentaryFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS, Long mainfspid) throws InputDataValidationException, FlightDisabledException
    {   Set<ConstraintViolation<FlightSchedulePlan>>constraintViolations = validator.validate(newFSP);

        if (f.getDisabledFlight()) {
            throw new FlightDisabledException("Flight has been disabled. Flight Schedule Plan cannot be created!");
        }
        
            
        if(constraintViolations.isEmpty())
        {
            FlightSchedulePlan mainfsp = new FlightSchedulePlan();
            try 
            {
                mainfsp = retrieveFSPfByFSPId(mainfspid);
            } catch (FlightSchedulePlanNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
                newFSP.setFlight(f);
                newFSP.setComplimentaryfsp(mainfsp);
                mainfsp.setComplimentaryfsp(newFSP);
                em.persist(newFSP);


                Flight flight = em.find(Flight.class, f.getFlightId());
                flight.getFlightscheduleplans().add(newFSP);
                
                

                em.flush();
                return newFSP.getFlightscheduleplanid();

          

        } else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
        // Persist the new FlightSchedulePlan
    
        //Long newFlightScheduleid = flightScheduleSessionBean.createNewFlightSchedule(newFS, newFSP.getFlightscheduleplanid());    
        
    }

    

    @Override
    public Long createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS) throws InputDataValidationException, FlightDisabledException
    {   Set<ConstraintViolation<FlightSchedulePlan>>constraintViolations = validator.validate(newFSP);

        if (f.getDisabledFlight()) {
            throw new FlightDisabledException("Flight has been disabled. Flight Schedule Plan cannot be created!");
        }
        
            
        if(constraintViolations.isEmpty())
        {
            
                 newFSP.setFlight(f);
                em.persist(newFSP);


                Flight flight = em.find(Flight.class, f.getFlightId());
                flight.getFlightscheduleplans().add(newFSP);

                em.flush();
                return newFSP.getFlightscheduleplanid();

          

        } else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
        // Persist the new FlightSchedulePlan
    
        //Long newFlightScheduleid = flightScheduleSessionBean.createNewFlightSchedule(newFS, newFSP.getFlightscheduleplanid());    
        
    }
    
    @Override
    public FlightSchedulePlan retrieveFSPfByFSPId(Long fspid) throws FlightSchedulePlanNotFoundException
    {
        FlightSchedulePlan fsp = em.find(FlightSchedulePlan.class, fspid);
        
        if(fsp != null)
        {
            fsp.getFlightschedules().size();
            fsp.getFares().size();
            return fsp;
        }
        else
        {
            throw new FlightSchedulePlanNotFoundException("FlightSchedulePlan ID " + fspid + " does not exist!");
        }
    }
    
    @Override
    public List<FlightSchedulePlan> retrieveAllFlightSchedulePlan()
    {   
        Query query = em.createQuery("SELECT fsp FROM FlightSchedulePlan fsp ORDER BY fsp.flight.flightNumber ASC, fsp.startDate DESC");
        
        List<FlightSchedulePlan> fsplist = query.getResultList();
        List<FlightSchedulePlan> answer = new ArrayList<>();
        for(FlightSchedulePlan fsp : fsplist)
        {
            if(!answer.contains(fsp))
            {
                answer.add(fsp);
                if(fsp.getComplimentaryfsp() != null)
                {
                    answer.add(fsp.getComplimentaryfsp());
                }
            }
        }
        return answer;
  
    }
    
    @Override
    public List<FlightSchedulePlan> retrieveFlightSchedulePlanByFlightID(Long flightId)
    {
        TypedQuery<FlightSchedulePlan> query = em.createQuery(
        "SELECT fsp FROM FlightSchedulePlan fsp WHERE fsp.flight.flightId = :flightId",
        FlightSchedulePlan.class
    );
    query.setParameter("flightId", flightId);
    return query.getResultList();
    }
    
    @Override
    public FlightSchedulePlan retrieveFlightSchedulePlanByFlightNumber(String flightNumber) {
        Query query = em.createQuery("SELECT fsp FROM FlightSchedulePlan fsp WHERE fsp.flight.flightNumber = :inFlightNumber");
        query.setParameter("inFlightNumber", flightNumber);
        return (FlightSchedulePlan) query.getSingleResult();
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<FlightSchedulePlan>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
    
  
    @Override
    public void updateFlightSchedulePlan(List<Fare> fares, Long fspid) throws FlightDisabledException, FlightSchedulePlanNotFoundException
    {
        
        FlightSchedulePlan fsp = em.find(FlightSchedulePlan.class, fspid);
        if(fsp == null)
        {
            throw new FlightSchedulePlanNotFoundException("FlightSchedulePlan with id: " + fspid + " does not exist");
        }
        if(fsp.getFlight().getDisabledFlight()){
            throw new FlightDisabledException("Flight has been disabled. Flight Schedule Plan cannot be updated!");
        }
        
        //List<Fare> currFares = fsp.getFares();
    
        // Clear the current fares associated with the FlightSchedulePlan
        //currFares.clear();

        // Add the new fares provided in the method argument
        for (Fare fare : fares) {
            fsp.getFares().remove(fare);
        }     
    }
    
    @Override
    public void deleteFlightSchedulePlan(Long fspid) throws FlightSchedulePlanNotFoundException
    {   
        FlightSchedulePlan fsp = em.find(FlightSchedulePlan.class, fspid);
        Flight f = em.find(Flight.class, fsp.getFlight().getFlightId());
        if(fsp == null)
        {
            throw new FlightSchedulePlanNotFoundException("FlightSchedulePlan with id: " + fspid + " does not exist");
        }
        
        //fsp is reserved. cannot delete. just set to disabled
        if(fsp.getReserved())
        {
            fsp.setDisabled(true);
        } else {
            f.getFlightscheduleplans().remove(fsp);
            em.remove(fsp);
        }
    }
    
}


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

