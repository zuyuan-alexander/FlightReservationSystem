/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Passenger;
import javax.ejb.Remote;
import util.exception.InputDataValidationException;
import util.exception.PassengerAlreadyExistsException;
import util.exception.PassengerNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zuyua
 */
@Remote
public interface PassengerSessionBeanRemote {

    public Passenger retrievePassengerByPassportNumber(String passportnum) throws PassengerNotFoundException;

    public Long createNewPassenger(Passenger newPassenger, Long seatid, Long fsid) throws PassengerAlreadyExistsException, UnknownPersistenceException, InputDataValidationException;
    
}
