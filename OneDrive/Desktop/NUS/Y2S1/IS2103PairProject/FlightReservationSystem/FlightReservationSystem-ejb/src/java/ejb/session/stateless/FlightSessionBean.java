/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Flight;
import entity.FlightRoute;
import java.util.List;
import java.util.Set;
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
import util.exception.FlightNotFoundException;
import util.exception.FlightNumberExistsException;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateFlightException;

/**
 *
 * @author alvintjw
 */
@Stateless
public class FlightSessionBean implements FlightSessionBeanRemote, FlightSessionBeanLocal {

    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    
    
    public FlightSessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    @Override
    public Flight retrieveFlightByFlightNumber(String flightnumber) throws FlightNotFoundException
    {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :inflightNumber");
        query.setParameter("inflightNumber", flightnumber);
        
        try
        {
            Flight f = (Flight)query.getSingleResult();
            f.getFlightscheduleplans().size();
            return f;
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new FlightNotFoundException("Flight number: " + flightnumber + " does not exist!");
        }
    }
    
    @Override
    public Flight retrieveFlightByFlightId(Long flightId) throws FlightNotFoundException {
        Flight flight = em.find(Flight.class, flightId);
        
        if(flight != null)
        {
            return flight;
        }
        else
        {
            throw new FlightNotFoundException("Flight with Flight ID " + flightId + " does not exist!");
        }
    }
    
    @Override
    public Long createNewFlight(Flight newFlight) throws FlightNumberExistsException, UnknownPersistenceException, InputDataValidationException
    {
        Set<ConstraintViolation<Flight>>constraintViolations = validator.validate(newFlight);
        
        if(constraintViolations.isEmpty())
        {
            try
            {
                em.persist(newFlight);
                em.flush();

                return newFlight.getFlightId();
            }
            catch(PersistenceException ex)
            {
                if(ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException"))
                {
                    if(ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException"))
                    {
                        throw new FlightNumberExistsException();
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
    public Long createComplementaryFlight(Flight mainFlight, String complementaryFlightNumber) throws FlightNotFoundException, InputDataValidationException, UpdateFlightException  {
        Flight complementaryFlight = new Flight(complementaryFlightNumber);
        complementaryFlight.setAircraftConfiguration(mainFlight.getAircraftConfiguration());
        complementaryFlight.setFlightRoute(mainFlight.getFlightRoute().getReturnFlightRoute());
        
        mainFlight.setReturnFlight(Boolean.TRUE);
        mainFlight.setComplimentaryFlight(complementaryFlight);
            
        updateFlight(mainFlight);
        em.persist(complementaryFlight);
        em.flush();
        
        return complementaryFlight.getFlightId();
    }
    
    @Override
    public List<Flight> viewAllFlight() {
        Query query = em.createQuery("SELECT f FROM Flight f ORDER BY f.flightNumber ASC, f.complimentaryFlight DESC");
        return query.getResultList();
    }
    
    @Override
    public Long updateFlight(Flight flight) throws FlightNotFoundException, InputDataValidationException, UpdateFlightException {
        if(flight != null && flight.getFlightId() != null)
        {
            Set<ConstraintViolation<Flight>>constraintViolations = validator.validate(flight);
        
            if(constraintViolations.isEmpty())
            {
                Flight flightToUpdate = retrieveFlightByFlightId(flight.getFlightId());

                if(flightToUpdate.getFlightNumber().equals(flight.getFlightNumber()))
                {
                    flightToUpdate.setFlightRoute(flight.getFlightRoute());
                    flightToUpdate.setAircraftConfiguration(flight.getAircraftConfiguration());
                    //flightToUpdate.setReturnFlight(Boolean.FALSE);
                    //flightToUpdate.setComplimentaryFlight(null);
                    return flightToUpdate.getFlightId();
                }
                else
                {
                    throw new UpdateFlightException("Flight Number of flight record to be updated does not match the existing record");
                }
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        }
        else
        {
            throw new FlightNotFoundException("Flight ID not provided for flight to be updated");
        }
    }
    
    @Override
    public void deleteFlight(Long flightId) throws FlightNotFoundException {
        // the flight id you input is actually a return flight
        try {
            Flight mainFlight = checkReturnFlight(flightId);

            if (mainFlight != null) {
                mainFlight.setReturnFlight(Boolean.FALSE);
                mainFlight.setComplimentaryFlight(null);
            }
            
            Flight flight = retrieveFlightByFlightId(flightId);

            if (flight.getFlightscheduleplans().isEmpty()) {
                // flight route is not used at all
                //flight.setAircraftConfiguration(new AircraftConfiguration());
                //flight.setFlightRoute(new FlightRoute());

                em.remove(flight);     
            } else {
                // flight route has at least a lfight, we cannot remove it directly
                flight.setDisabledFlight(Boolean.TRUE);
            }
        } catch (NoResultException ex) {
            Flight flight = retrieveFlightByFlightId(flightId);

            if (flight.getFlightscheduleplans().isEmpty()) {
                // flight route is not used at all
                //flight.setAircraftConfiguration(new AircraftConfiguration());
                //flight.setFlightRoute(new FlightRoute());

                em.remove(flight);     
            } else {
                // flight route has at least a lfight, we cannot remove it directly
                flight.setDisabledFlight(Boolean.TRUE);
            }
        }
    }
    
    public Flight checkReturnFlight(Long returnFlightId) {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.complimentaryFlight.flightId = :inReturnFlightId");
        query.setParameter("inReturnFlightId", returnFlightId);
        return (Flight) query.getSingleResult();
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Flight>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
