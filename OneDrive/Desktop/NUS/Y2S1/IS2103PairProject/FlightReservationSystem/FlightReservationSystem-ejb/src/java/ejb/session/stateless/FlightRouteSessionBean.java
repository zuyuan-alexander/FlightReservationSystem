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
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AircraftTypeNotFoundException;
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
        Query query = em.createQuery("SELECT fr FROM FlightRoute fr ORDER BY fr.origin ASC, fr.returnFlightRoute.origin ASC");
        return query.getResultList();
    }
    
    @Override
    public void deleteFlightRoute(Long flightRouteId) throws FlightRouteNotFoundException {
        FlightRoute mainRoute = checkReturnFlightRoute(flightRouteId);
        
        if (mainRoute != null) {
            // the flight route we are prompting is actually a complementary return route
            // we must update the main route before deleting the return route
            mainRoute.setReturnFlight(Boolean.FALSE);
            mainRoute.setReturnFlightRoute(null);
        }
        
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
    
    public FlightRoute checkReturnFlightRoute(Long returhFlightRouteId) {
        Query query = em.createQuery("SELECT fr FROM FlightRoute fr WHERE fr.returnFlightRoute.flightRouteId = :inReturnFlightRouteId");
        query.setParameter("inReturnFlightRouteId", returhFlightRouteId);
        return (FlightRoute) query.getSingleResult();
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
    
    @Override
    public FlightRoute retrieveFlightRouteByOriginDestination(String origin, String destination) throws FlightRouteNotFoundException {
        Query query = em.createQuery("SELECT fr FROM FlightRoute fr WHERE fr.origin = :inOrigin AND fr.destination = :inDestination");
        query.setParameter("inOrigin", origin).setParameter("inDestination", destination);
        
        try {
            return (FlightRoute) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new FlightRouteNotFoundException("Flight Route with Origin " + origin + " and Destination " + destination + " does not exist!");
        }
    }
    
}
