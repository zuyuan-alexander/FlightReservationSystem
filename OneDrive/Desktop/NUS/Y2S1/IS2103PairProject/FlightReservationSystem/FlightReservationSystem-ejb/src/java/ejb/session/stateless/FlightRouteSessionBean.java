/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import entity.Flight;
import entity.FlightRoute;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.AircraftTypeNotFoundException;
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
@Stateless
public class FlightRouteSessionBean implements FlightRouteSessionBeanRemote, FlightRouteSessionBeanLocal {
    
    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private AirportSessionBeanLocal airportSessionBeanLocal;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public FlightRouteSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public Long createFlightRoute(FlightRoute flightRoute) throws AirportNotFoundException, FlightRouteAlreadyExistedException, UnknownPersistenceException, InputDataValidationException {
        Set<ConstraintViolation<FlightRoute>>constraintViolations = validator.validate(flightRoute);
        
        if(constraintViolations.isEmpty())
        {
            try
            {
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

                    returnFlightRoute.setReturnFlightRoute(flightRoute);
                    returnFlightRoute.setReturnFlight(Boolean.TRUE);

                    em.persist(returnFlightRoute);
                }

                em.flush();

                return flightRoute.getFlightRouteId();
            }
            catch(PersistenceException ex)
            {
                if(ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException"))
                {
                    if(ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException"))
                    {
                        throw new FlightRouteAlreadyExistedException("Flight Route already existed!");
                    }
                    else
                    {
                        throw new UnknownPersistenceException(ex.getMessage());
                    }
                }
                else
                {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public List<FlightRoute> viewAllFlightRoutes() {
        Query query = em.createQuery("SELECT fr FROM FlightRoute fr ORDER BY fr.origin ASC, fr.destination ASC");
        List<FlightRoute> frList = query.getResultList();
        List<FlightRoute> answer = new ArrayList<>();
        for (FlightRoute fr : frList) {
            if (!answer.contains(fr)) {
                answer.add(fr);
                
                if (fr.getReturnFlight()) {
                    // it has a complementary flight route
                    answer.add(fr.getReturnFlightRoute());
                }
            }
        }
        return answer;
    }
    
    @Override
    public void deleteFlightRoute(Long flightRouteId) throws FlightRouteNotFoundException {
        // the flight id you input is actually a return flight
        try {
            FlightRoute mainFR = checkReturnFlightRoute(flightRouteId);

            if (mainFR != null) {
                mainFR.setReturnFlight(Boolean.FALSE);
                mainFR.setReturnFlightRoute(null);
            }
            
            FlightRoute flightRoute = retrieveFlightRouteByFlightRouteId(flightRouteId);
            flightRoute.getFlights().size();
            System.out.println("test: " + flightRoute.getFlights().isEmpty());
            if (flightRoute.getFlights().isEmpty()) {
                // flight route is not used at all
                //flight.setAircraftConfiguration(new AircraftConfiguration());
                //flight.setFlightRoute(new FlightRoute());

                em.remove(flightRoute);     
            } else {
                // flight route has at least a lfight, we cannot remove it directly
                flightRoute.setDisabledFlight(Boolean.TRUE);
            }
        } catch (NoResultException ex) {
            FlightRoute flightRoute = retrieveFlightRouteByFlightRouteId(flightRouteId);

            if (flightRoute.getFlights().isEmpty()) {
                // flight route is not used at all
                //flight.setAircraftConfiguration(new AircraftConfiguration());
                //flight.setFlightRoute(new FlightRoute());

                em.remove(flightRoute);     
            } else {
                // flight route has at least a lfight, we cannot remove it directly
                flightRoute.setDisabledFlight(Boolean.TRUE);
            }
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
    
    @Override
    public Long updateFlightRoute(FlightRoute flightRoute) throws UpdateFlightRouteException, FlightRouteNotFoundException, InputDataValidationException {
        if(flightRoute != null && flightRoute.getFlightRouteId()!= null)
        {
            Set<ConstraintViolation<FlightRoute>>constraintViolations = validator.validate(flightRoute);
        
            if(constraintViolations.isEmpty())
            {
                FlightRoute flightRouteToUpdate = retrieveFlightRouteByFlightRouteId(flightRoute.getFlightRouteId());

                if(flightRouteToUpdate.getOriginToDestination().equals(flightRouteToUpdate.getOriginToDestination()))
                {
                    flightRouteToUpdate.setAirports(flightRoute.getAirports());
                    flightRouteToUpdate.setFlights(flightRoute.getFlights());
                    flightRouteToUpdate.setReturnFlightRoute(flightRoute.getReturnFlightRoute());
                    flightRouteToUpdate.setReturnFlight(flightRoute.getReturnFlight());
                    flightRouteToUpdate.setDisabledFlight(flightRoute.getDisabledFlight());
                    return flightRouteToUpdate.getFlightRouteId();
                }
                else
                {
                    throw new UpdateFlightRouteException("Flight Number of flight record to be updated does not match the existing record");
                }
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        }
        else
        {
            throw new FlightRouteNotFoundException("Flight Route ID not provided for flight route to be updated");
        }
    }
    
    @Override
    public void addFlight(FlightRoute flightRoute, Flight flight) {
        flightRoute.getFlights().size();
        flightRoute.getFlights().add(flight);
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<FlightRoute>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
    
}
