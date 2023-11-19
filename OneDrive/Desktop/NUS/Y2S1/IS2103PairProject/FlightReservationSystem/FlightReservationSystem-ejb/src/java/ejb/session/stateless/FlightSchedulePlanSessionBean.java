/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Fare;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import util.exception.FlightDisabledException;
import util.exception.FlightSchedulePlanNotFoundException;

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

    

    @Override
    public Long createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS) throws FlightDisabledException
    {
        if (f.getDisabledFlight()) {
            throw new FlightDisabledException("Flight has been disabled. Flight Schedule Plan cannot be created!");
        }
        
        // Persist the new FlightSchedulePlan
        newFSP.setFlight(f);
        em.persist(newFSP);
        
      
        Flight flight = em.find(Flight.class, f.getFlightId());
        flight.getFlightscheduleplans().add(newFSP);
        
        em.flush();
        return newFSP.getFlightscheduleplanid();
           
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

