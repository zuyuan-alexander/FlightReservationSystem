/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Seat;
import javax.ejb.Remote;
import util.exception.InputDataValidationException;
import util.exception.SeatNotFoundException;

/**
 *
 * @author zuyua
 */
@Remote
public interface SeatSessionBeanRemote {
    
    public Seat createSeats(Seat seat) throws InputDataValidationException ;
    

    public Seat retrieveSeatBySeatLetterAndRowNumber(Character seatLetter, Integer rowNumber, Long cabinClassId) throws SeatNotFoundException;
    
    public Seat retrieveSeatBySeatId(Long id) throws SeatNotFoundException;

}
