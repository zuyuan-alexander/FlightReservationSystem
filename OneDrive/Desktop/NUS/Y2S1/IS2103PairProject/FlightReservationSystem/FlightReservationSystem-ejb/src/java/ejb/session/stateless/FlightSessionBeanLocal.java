/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import javax.ejb.Local;
import util.exception.FlightNotFoundException;
import util.exception.FlightNumberExistsException;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author alvintjw
 */
@Local
public interface FlightSessionBeanLocal {
    public Flight retrieveFlightByFlightNumber(String flightnumber) throws FlightNotFoundException;
    public Long createNewFlight(Flight newFlight) throws FlightNumberExistsException, UnknownPersistenceException, InputDataValidationException;
}
