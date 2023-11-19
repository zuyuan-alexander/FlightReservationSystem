/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.FlightReservation;
import entity.FlightSchedule;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import util.enumeration.CabinClassTypeEnum;
import util.enumeration.TripTypeEnum;
import util.exception.FlightReservationNotFoundException;

/**
 *
 * @author zuyua
 */
@Local
public interface FlightReservationSessionBeanLocal {

    public List<FlightSchedule> searchFlightDirectFlight(String departureAirport, String destinationAirport, Date date, Integer numOfPassengers, CabinClassTypeEnum cabinClassType);

    public List<FlightSchedule> searchFlightConnectingFlight(String departureAirport, String destinationAirport, Date date, Integer numOfPassengers, CabinClassTypeEnum cabinClassType);

    public List<FlightReservation> viewMyFlightReservations(Customer customer);

    public FlightReservation retrieveFlightReservationById(Long flightReservationId) throws FlightReservationNotFoundException;
     public Long reserveFlightMain(Long customerid, Long fsid, Long passengerid, TripTypeEnum tripType);
    
    
}
