/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClass;
import entity.Seat;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.SeatStatusEnum;

/**
 *
 * @author zuyua
 */
@Stateless
public class CabinClassSessionBean implements CabinClassSessionBeanRemote, CabinClassSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private SeatSessionBeanLocal seatSessionBeanLocal;

    public CabinClassSessionBean() {
    }
    
    @Override
    public CabinClass createCabinClass(CabinClass cabinClass) {
        Integer counter = 0;
        
        Integer numOfAisles = cabinClass.getNumOfAisles();
        Integer numOfRows = cabinClass.getNumOfRows();
        Integer numOfSeatsAbreast = cabinClass.getNumOfSeatsAbreast();
        String actualSeatingConfiguration = cabinClass.getActualSeatConfiguration();
        
        Integer maxCapacity = cabinClass.getMaxCapacity();
        
        em.persist(cabinClass);
        em.flush();
        
        // create the seats
        for(int i=1; i<=numOfRows; i++) {
            for (int j=1; j<=numOfSeatsAbreast; j++) {
                if (counter > maxCapacity) {
                    break;
                }
                
                char seatLetter = (char) ('A' + (j - 1));
                
                Seat seat = new Seat(i, seatLetter, SeatStatusEnum.AVAILABLE);
                
                seatSessionBeanLocal.createSeats(seat);
                
                cabinClass.getSeats().add(seat);
                seat.setCabinClass(cabinClass);
                
                counter++;
            }
        }
        
        return cabinClass;
    }
}
