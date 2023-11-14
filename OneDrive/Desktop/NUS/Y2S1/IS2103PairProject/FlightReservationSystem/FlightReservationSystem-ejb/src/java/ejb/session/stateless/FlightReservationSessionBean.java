/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.FlightReservation;
import entity.FlightSchedule;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.TripTypeEnum;
import util.exception.FlightReservationNotFoundException;

/**
 *
 * @author zuyua
 */
@Stateless
public class FlightReservationSessionBean implements FlightReservationSessionBeanRemote, FlightReservationSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public FlightReservationSessionBean() {
    }

    @Override
    public List<FlightReservation> searchFlight(TripTypeEnum tripType, String departureAirport, String destinationAirport, Date date, Integer numOfPassengers, Boolean directConnectingFlight, Boolean cabinClassPreference) {
        List<FlightSchedule> flightSchedulesOnDate;
        List<FlightSchedule> flightSchedulesThreeDaysBeforeDate;
        List<FlightSchedule> flightScheduleThreeDaysAfterDate;
        
        // display the flight schedule availability and cabin class availability in the client
        // display the price per passenger and total price for all passenger in the client (retrieve fare from the flight schedule)
        return null;
    }
    
    // reserve flight will create a passenger from customer
    
    @Override
    public List<FlightReservation> viewMyFlightReservations(Customer customer) {
        Query query = em.createQuery("SELECT fr FROM FlightReservation fr");
        return query.getResultList();
    }
    
    // viewMyFlightReservationDetails in the client (by retrieving the flight reservation using the id)
    
    @Override
    public FlightReservation retrieveFlightReservationById(Long flightReservationId) throws FlightReservationNotFoundException {
        FlightReservation flightReservation = em.find(FlightReservation.class, flightReservationId);
        
        if(flightReservation != null)
        {
            return flightReservation;
        }
        else
        {
            throw new FlightReservationNotFoundException("Flight Reservation with Id " + flightReservationId + " does not exist!");
        }
    }

    
}
