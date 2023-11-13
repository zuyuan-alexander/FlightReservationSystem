/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Flight;
import entity.FlightSchedule;
import entity.Seat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zuyua
 */
@Stateless
public class ManagementSessionBean implements ManagementSessionBeanRemote, ManagementSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public ManagementSessionBean() {
    }

    
/*
    public List<Seat> viewSeatInventory(Flight flight, FlightSchedule flightSchedule) {
        //AircraftConfiguration aircraftConfiguration = flight.getAircraft();
        AircraftConfiguration aircraftConfiguration = new AircraftConfiguration();
        
        Integer numOfCabinClasses = aircraftConfiguration.getNumOfCabinClass();
        
        
    }
*/
}
