/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Seat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zuyua
 */
@Stateless
public class SeatSessionBean implements SeatSessionBeanRemote, SeatSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public SeatSessionBean() {
    }

    @Override
    public Seat createSeats(Seat seat) {
        em.persist(seat);
        return seat;
    }
    
    @Override
    public Seat retrieveSeatBySeatLetterAndRowNumber(Character seatLetter, Integer rowNumber) {
        Query query = em.createQuery("SELECT s FROM Seat s WHERE s.seatLetter = :inSeatLetter AND s.rowNumber = :inRowNumber");
        query.setParameter("inSeatLetter", seatLetter).setParameter("inRowNumber", rowNumber);
        return (Seat) query.getSingleResult();
    }

    
}
