/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.CabinClass;
import entity.Flight;
import entity.FlightReservation;
import entity.FlightSchedule;
import entity.Passenger;
import entity.Seat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.SeatStatusEnum;
import util.exception.FlightNotFoundException;

/**
 *
 * @author zuyua
 */
@Stateless
public class ManagementSessionBean implements ManagementSessionBeanRemote, ManagementSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private CabinClassSessionBeanLocal cabinClassSessionBeanLocal;
    
    @EJB
    private FlightSessionBeanLocal flightSessionBeanLocal;

    public ManagementSessionBean() {
    }
    
/*
    public List<List<Integer>> viewSeatInventory(Long flightNumber, FlightSchedule flightSchedule) {
        // Flight flight = flightSessionBeanLocal.retrieveFlightByFlightNumber(flightNumber);
        // AircraftConfiguration aircraftConfiguration = flight.getAircraft();
        List<List<Integer>> answer = new ArrayList<>();
        AircraftConfiguration aircraftConfiguration = new AircraftConfiguration();
        
        for (CabinClass cabinClass : aircraftConfiguration.getCabinClasses()) {
            List<Integer> numOfSeats = cabinClassSessionBeanLocal.calculateNumOfSeats(cabinClass);
            
            answer.add(numOfSeats);
        }
        
        // the total number of available, reserved and balance seats can be calculated in the client (when iterating through the list via a for loop)
        return answer;
    }
    */
    
    public List<Passenger> viewSeatsInventory(CabinClass cabinClass, FlightSchedule flightSchedule) {
        // this part may have some error, need to check with the jpql lab first
        Query query = em.createQuery("SELECT p FROM Passenger p WHERE p.seat.cabinClass.cabinClassId = :inCabinClassId AND p.flightSchedule.flightscheduleid = :inFlightScheduleId");
        query.setParameter("inCabinClassId", cabinClass.getCabinClassId()).setParameter("inFlightScheduleId", flightSchedule.getFlightscheduleid());
        
        return query.getResultList();
    }
    
    public List<Passenger> viewFlightReservations(CabinClass cabinClass, FlightSchedule flightSchedule) {
        Query query = em.createQuery("SELECT p FROM Passenger p WHERE p.seat.cabinClass.cabinClassId = :inCabinClassId AND p.flightSchedule.flightscheduleid = :inFlightScheduleId");
        query.setParameter("inCabinClassId", cabinClass.getCabinClassId()).setParameter("inFlightScheduleId", flightSchedule.getFlightscheduleid());
        
        return query.getResultList();
    }
    
    /*
    public Integer calculateNumOfAvailabeSeats(String flightNumber, FlightSchedule flightSchedule) throws FlightNotFoundException {
        Flight flight = flightSessionBeanLocal.retrieveFlightByFlightNumber(flightNumber);
        List<CabinClass> cabinClassList = flight.getAircraftConfiguration().getCabinClasses();
        List<Seat> seats = flightSchedule.getSeats();
        Integer counter = 0;
        
        for (CabinClass cabinClass : cabinClassList) {
            for (Seat seat : seats) {
                if (seat.getSeatStatus().equals(SeatStatusEnum.AVAILABLE) && seat.getCabinClassType().equals(this)) {
                    counter++;
                }
            }
        }
        
        return counter;
    }
    
    public Integer calculateNumOfReservedSeats(CabinClass cabinClass) {
        List<Seat> seats = cabinClass.getSeats();
        Integer counter = 0;
        
        for (Seat seat : seats) {
            if (seat.getSeatStatus().equals(SeatStatusEnum.RESERVED)) {
                counter++;
            }
        }
        
        return counter;
    }
    
    public List<Integer> calculateNumOfSeats(CabinClass cabinClass) {
        List<Integer> answer = new ArrayList<>();
        Integer numOfAvailableSeats = calculateNumOfAvailabeSeats(cabinClass);
        answer.add(numOfAvailableSeats);
        
        Integer numOfReservedSeats = calculateNumOfReservedSeats(cabinClass);
        answer.add(numOfReservedSeats);
        
        Integer numOfBlanceSeats = cabinClass.getMaxCapacity() - numOfAvailableSeats - numOfReservedSeats;
        answer.add(numOfAvailableSeats);
        
        return answer;
    }
    */
}
