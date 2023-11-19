/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightRoute;
import java.util.List;
import javax.ejb.Local;
import util.exception.AirportNotFoundException;
import util.exception.FlightRouteAlreadyExistedException;
import util.exception.FlightRouteNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateFlightRouteException;

/**
 *
 * @author zuyua
 */
@Local
public interface FlightRouteSessionBeanLocal {
    
    public Long createFlightRoute(FlightRoute flightRoute) throws AirportNotFoundException, FlightRouteAlreadyExistedException, UnknownPersistenceException, InputDataValidationException;
    
    public List<FlightRoute> viewAllFlightRoutes();
    
    public void deleteFlightRoute(Long flightRouteId) throws FlightRouteNotFoundException;
    
    public FlightRoute retrieveFlightRouteByFlightRouteId(Long flightRouteId) throws FlightRouteNotFoundException;

    public FlightRoute retrieveFlightRouteByOriginDestination(String origin, String destination) throws FlightRouteNotFoundException;

    public Long updateFlightRoute(FlightRoute flightRoute) throws UpdateFlightRouteException, FlightRouteNotFoundException, InputDataValidationException;

    public void addFlight(FlightRoute flightRoute, Flight flight);
}
