/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.FlightScheduleNotFoundException;

/**
 *
 * @author alvintjw
 */
@Stateless
public class FlightScheduleSessionBean implements FlightScheduleSessionBeanRemote, FlightScheduleSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public Long createNewFlightSchedule(FlightSchedule fs, Long newFSPid)
    {
        FlightSchedulePlan newFSP = em.find(FlightSchedulePlan.class, newFSPid);
        fs.setFlightSchedulePlan(newFSP);
        newFSP.getFlightschedules().add(fs);
        em.persist(fs);
       
        em.flush();
        return fs.getFlightscheduleid();
    }
    
    @Override
    public FlightSchedule retrieveFlightScheduleById(Long flightScheduleid) throws FlightScheduleNotFoundException
    {
        FlightSchedule f = em.find(FlightSchedule.class, flightScheduleid);
        
        if(f != null)
        {
            return f;
        }
        else
        {
            throw new FlightScheduleNotFoundException("Staff ID " + flightScheduleid + " does not exist!");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
