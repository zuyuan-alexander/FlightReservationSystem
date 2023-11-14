/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.FlightReservation;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import util.enumeration.TripTypeEnum;
import util.exception.FlightReservationNotFoundException;

/**
 *
 * @author zuyua
 */
@Remote
public interface FlightReservationSessionBeanRemote {
    
    public List<FlightReservation> searchFlight(TripTypeEnum tripType, String departureAirport, String destinationAirport, Date departureDate, Integer numOfPassengers, Boolean directConnectingFlight, Boolean cabinClassPreference);

    public List<FlightReservation> searchFlightWithReturnDate(TripTypeEnum tripType, String departureAirport, String destinationAirport, Date departureDate, Date returnDate, Integer numOfPassengers, Boolean directConnectingFlight, Boolean cabinClassPreference);

    public FlightReservation retrieveFlightReservationById(Long flightReservationId) throws FlightReservationNotFoundException;

    public List<FlightReservation> viewMyFlightReservations(Customer customer);
    
}
