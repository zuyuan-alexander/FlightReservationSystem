/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.CabinClass;
import entity.Flight;
import entity.FlightReservation;
import entity.FlightSchedule;
import entity.Seat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zuyua
 */
@Stateless
public class ManagementSessionBean implements ManagementSessionBeanRemote, ManagementSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private CabinClassSessionBeanLocal cabinClassSessionBeanLocal;

    public ManagementSessionBean() {
    }
    
/*
    public List<List<Integer>> viewSeatInventory(Long flightNumber, FlightSchedule flightSchedule) {
        // Flight flight = flightSessionBeanLocal.retrieveFlightByFlightNumber(flightNumber);
        // AircraftConfiguration aircraftConfiguration = flight.getAircraft();
        List<List<Integer>> answer = new ArrayList<>();
        AircraftConfiguration aircraftConfiguration = new AircraftConfiguration();
        
        for (CabinClass cabinClass : aircraftConfiguration.getCabinClasses()) {
            List<Integer> numOfSeats = cabinClassSessionBeanLocal.calculateNumOfSeats(cabinClass);
            
            answer.add(numOfSeats);
        }
        
        // the total number of available, reserved and balance seats can be calculated in the client (when iterating through the list via a for loop)
        return answer;
    }
    */
    public List<FlightReservation> viewFlightReservations(Long flightNumber, FlightSchedule flightSchedule) {
        return null;
    }

}
