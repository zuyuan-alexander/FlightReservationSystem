/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import entity.FlightRoute;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AirportNotFoundException;
import util.exception.FlightRouteNotFoundException;

/**
 *
 * @author zuyua
 */
@Stateless
public class FlightRouteSessionBean implements FlightRouteSessionBeanRemote, FlightRouteSessionBeanLocal {
    
    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private AirportSessionBeanLocal airportSessionBeanLocal;

    public FlightRouteSessionBean() {
    }
    
    @Override
    public Long createFlightRoute(FlightRoute flightRoute) throws AirportNotFoundException {
        
        // associate the airports of the origin and destination with the flight route
        
        String originIATA = flightRoute.getOrigin();
        String destinationIATA = flightRoute.getDestination();
        
        Airport originAirport = airportSessionBeanLocal.retrieveAirportByIATACode(originIATA);
        Airport destinationAirport = airportSessionBeanLocal.retrieveAirportByIATACode(destinationIATA);
        
        flightRoute.getAirports().add(originAirport);
        flightRoute.getAirports().add(destinationAirport);
        
        em.persist(flightRoute);
        
        // if the user wants a complementary return route, create the return route (reverse of the current flight route)
        if (flightRoute.getReturnFlight()) {
            // the user wants a return route
            FlightRoute returnFlightRoute = new FlightRoute(destinationIATA, originIATA);
            
            flightRoute.setReturnFlightRoute(returnFlightRoute);
            
            returnFlightRoute.getAirports().add(destinationAirport);
            returnFlightRoute.getAirports().add(originAirport);
            
            em.persist(returnFlightRoute);
        }
        
        em.flush();
        
        return flightRoute.getFlightRouteId();
    }
    
    @Override
    public List<FlightRoute> viewAllFlightRoutes() {
        Query query = em.createQuery("SELECT fr FROM FlightRoute fr ORDER BY fr.origin ASC");
        // have not set the condition for displaying the return flight route immediately below the main route
        return query.getResultList();
    }
    
    @Override
    public void deleteFlightRoute(Long flightRouteId) throws FlightRouteNotFoundException {
        FlightRoute flightRoute = retrieveFlightRouteByFlightRouteId(flightRouteId);
        
        // if flight route has at least a flight, we cannot remove it and have to set disabled to TRUE
        // if flight route is not used at all, we can remove it directly
        if (flightRoute.getFlights().isEmpty()) {
            // flight route is not used at all
            flightRoute.getAirports().clear();
            
            em.remove(flightRoute);
        } else {
            // flight route has at least a lfight, we cannot remove it directly
            flightRoute.setDisabledFlight(Boolean.TRUE);
        }
    }
    
    @Override
    public FlightRoute retrieveFlightRouteByFlightRouteId(Long flightRouteId) throws FlightRouteNotFoundException {
        FlightRoute flightRoute = em.find(FlightRoute.class, flightRouteId);
        
        if (flightRoute != null) {
            return flightRoute;
        } else {
            throw new FlightRouteNotFoundException("Flight Route with Flight Route Id " + flightRouteId + " does not exist!");
        }
    }
    
}
