/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
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
    public Long createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS) throws InputDataValidationException 
    {   Set<ConstraintViolation<FlightSchedulePlan>>constraintViolations = validator.validate(newFSP);
        
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
        
        return query.getResultList();
  
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
    
    /*
    @Override
    public List<FlightSchedule> retrieveFlightScheduleByFSP(Long fspId) throws FlightSchedulePlanNotFoundException {
        FlightSchedulePlan fsp = retrieveStaffByStaffId(fspId);
        fsp.getFlightschedules().size();
        fsp.getFares().size();  
        return fsp.getFlightschedules();
    }
    
    @Override
    public List<Fare> retrieveFareByFSPId(Long fspId) throws FlightSchedulePlanNotFoundException {
        FlightSchedulePlan fsp = retrieveStaffByStaffId(fspId);
        fsp.getFares().size();
        return fsp.getFares();
    }*/
}


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

