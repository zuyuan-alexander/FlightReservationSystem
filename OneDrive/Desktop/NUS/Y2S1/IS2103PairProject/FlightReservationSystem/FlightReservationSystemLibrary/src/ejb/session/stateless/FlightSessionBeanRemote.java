/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AircraftConfigurationNotFoundException;
import util.exception.FlightNotFoundException;
import util.exception.FlightNumberExistsException;
import util.exception.FlightRouteNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateFlightException;

/**
 *
 * @author alvintjw
 */
@Remote
public interface FlightSessionBeanRemote {

    public Flight retrieveFlightByFlightNumber(String flightnumber) throws FlightNotFoundException;

    public Long createNewFlight(Flight newFlight, Long flightRouteId, Long acnId) throws FlightNumberExistsException, UnknownPersistenceException, InputDataValidationException, FlightRouteNotFoundException, AircraftConfigurationNotFoundException;
    
    public Flight retrieveFlightByFlightId(Long flightId) throws FlightNotFoundException;
    public List<Flight> viewAllFlight();
    public Long updateFlight(Flight flight) throws FlightNotFoundException, InputDataValidationException, UpdateFlightException;
    public void deleteFlight(Long flightId) throws FlightNotFoundException;
    
    public Long createComplementaryFlight(Long mainFlightId, String complementaryFlightNumber, String aircraftConfigurationName) throws FlightNotFoundException, InputDataValidationException, UpdateFlightException, AircraftConfigurationNotFoundException;
}
