/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClass;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.Seat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.SeatStatusEnum;
import util.exception.FlightScheduleNotFoundException;

/**
 *
 * @author alvintjw
 */
@Stateless
public class FlightScheduleSessionBean implements FlightScheduleSessionBeanRemote, FlightScheduleSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private SeatSessionBeanLocal seatSessionBeanLocal;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public Long createNewFlightSchedule(FlightSchedule fs, Long newFSPid)
    {
        FlightSchedulePlan newFSP = em.find(FlightSchedulePlan.class, newFSPid);
        fs.setFlightSchedulePlan(newFSP);
        newFSP.getFlightschedules().add(fs);
        
        /*
        // when you create a flight schedule, you should initialize all the seats for the flight schedule
        List<CabinClass> cabinClassList = newFSP.getFlight().getAircraftConfiguration().getCabinClasses();
        
        for (CabinClass cabinClass : cabinClassList) {
            // initialize seat for every cabin class for that particular flight schedule
            seatInit(fs, cabinClass);
        }
        */
        
        em.persist(fs);
        em.flush();
        return fs.getFlightscheduleid();
    }
    
    @Override
    public FlightSchedule retrieveFlightScheduleById(Long flightScheduleid) throws FlightScheduleNotFoundException
    {
        FlightSchedule f = em.find(FlightSchedule.class, flightScheduleid);
        
        if(f != null)
        {
            return f;
        }
        else
        {
            throw new FlightScheduleNotFoundException("Staff ID " + flightScheduleid + " does not exist!");
        }
    }

    /*
    public void seatInit(FlightSchedule flightSchedule, CabinClass cabinClass) {
        Integer counter = 0;
        
        Integer numOfAisles = cabinClass.getNumOfAisles();
        Integer numOfRows = cabinClass.getNumOfRows();
        Integer numOfSeatsAbreast = cabinClass.getNumOfSeatsAbreast();
        String actualSeatingConfiguration = cabinClass.getActualSeatConfiguration();
        
        Integer maxCapacity = cabinClass.getMaxCapacity();
        
        // create the seats
        for(int i=1; i<=numOfRows; i++) {
            for (int j=1; j<=numOfSeatsAbreast; j++) {
                if (counter > maxCapacity) {
                    break;
                }
                
                char seatLetter = (char) ('A' + (j - 1));
                
                Seat seat = new Seat(i, seatLetter, SeatStatusEnum.AVAILABLE, cabinClass.getCabinClassType());
                seat.setFlightSchedule(flightSchedule);
                flightSchedule.getSeats().add(seat);
                
                seatSessionBeanLocal.createSeats(seat);
                
                //cabinClass.getSeats().add(seat);
                //seat.setCabinClass(cabinClass);
                // seat.setFlightSchedule(flighgSchedule);
                
                counter++;
            }
        }
    }
*/
}
