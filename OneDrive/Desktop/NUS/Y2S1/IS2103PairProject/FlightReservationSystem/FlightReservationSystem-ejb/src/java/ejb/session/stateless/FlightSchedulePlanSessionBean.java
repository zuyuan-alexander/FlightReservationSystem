/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightSchedule;
import javax.ejb.Stateless;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alvintjw
 */
@Stateless
public class FlightSchedulePlanSessionBean implements FlightSchedulePlanSessionBeanRemote, FlightSchedulePlanSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    

    /*
    public void createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS)    
    {
        //em.persist(newFSP)
        newFS.set(newFSP)
    //newFSP.getFS.add(newFS)
        //FSsessionbean.createNewFS(newFS)
        
        try {
            FlightSchedule scheduleInfo = parseFlightSchedule(input);
            /*
            System.out.println("Day of Week: " + scheduleInfo.getDayOfWeek());
            System.out.println("Departure Time: " + scheduleInfo.getDepartureTime());
            System.out.println("Start Date: " + scheduleInfo.getStartDate());
            System.out.println("End Date: " + scheduleInfo.getEndDate());
            System.out.println("Flight Duration: " + scheduleInfo.getEstimatedFlightDuration());
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

