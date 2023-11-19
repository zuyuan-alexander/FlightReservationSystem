/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Seat;
import javax.ejb.Local;
import util.exception.SeatNotFoundException;

/**
 *
 * @author zuyua
 */
@Local
public interface SeatSessionBeanLocal {

    public Seat createSeats(Seat seat);

    public Seat retrieveSeatBySeatLetterAndRowNumber(Character seatLetter, Integer rowNumber, Long cabinClassId)  throws SeatNotFoundException;

    public Seat retrieveSeatBySeatId(Long id) throws SeatNotFoundException;
    
}
