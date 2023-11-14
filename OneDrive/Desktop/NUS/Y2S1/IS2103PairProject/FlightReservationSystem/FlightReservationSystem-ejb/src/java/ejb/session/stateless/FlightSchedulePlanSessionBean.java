/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvintjw
 */
@Stateless
public class FlightSchedulePlanSessionBean implements FlightSchedulePlanSessionBeanRemote, FlightSchedulePlanSessionBeanLocal {

    @EJB
    private FlightScheduleSessionBeanRemote flightScheduleSessionBean;

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    

    @Override
    public void createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS)    
    {
        newFSP.setFlight(f);
        em.persist(newFSP);
        //f.getFlightscheduleplans().add(newFSP);
        //newFS.setFlightSchedulePlan(newFSP);
        //newFSP.getFlightschedules().add(newFS);
        //newFSP.setFlight(f);
 
        
        //flightScheduleSessionBean.createNewFlightSchedule(newFS);
        
    }
    
    /*
    private void generateFlightSchedules(FlightSchedulePlan flightSchedulePlan) {
        // Extract recurrence details from the flight schedule plan
        // Calculate and create flight schedules accordingly

        // Example: For weekly recurrence, create schedules for 52 weeks
        for (int i = 0; i < 52; i++) {
            FlightSchedule flightSchedule = new FlightSchedule();
            // Set schedule details based on recurrence pattern
            // ...

            // Link the flight schedule to the flight schedule plan
            flightSchedule.setFlightSchedulePlan(flightSchedulePlan);

            // Persist the new FlightSchedule to the database
            entityManager.persist(flightSchedule);
        }
    }

    
    private static FlightSchedule parseFlightSchedule(String input) throws ParseException {
        String[] tokens = input.split(", ");
        
        String dayOfWeek = tokens[0];
        String departureTime = tokens[1];
        String startDateStr = tokens[2];
        String endDateStr = tokens[3];
        String flightDurationStr = tokens[4];

        // Parse dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yy");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);

        // Parse flight duration
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH 'Hours' mm 'Minute'");
        Date flightDuration = timeFormat.parse(flightDurationStr);

        return new FlightScheduleInfo(dayOfWeek, departureTime, startDate, endDate, flightDuration);
    } */

    
    
}


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

