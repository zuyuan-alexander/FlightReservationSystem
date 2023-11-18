/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.Local;
import util.exception.FlightSchedulePlanNotFoundException;

/**
 *
 * @author alvintjw
 */
@Local
public interface FlightSchedulePlanSessionBeanLocal {
     public Long createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS);
     public FlightSchedulePlan retrieveStaffByStaffId(Long fspid) throws FlightSchedulePlanNotFoundException;
     public List<FlightSchedulePlan> retrieveAllFlightSchedulePlan();
     public List<FlightSchedulePlan> retrieveFlightSchedulePlanByFlightID(Long flightId);
    
    
}
