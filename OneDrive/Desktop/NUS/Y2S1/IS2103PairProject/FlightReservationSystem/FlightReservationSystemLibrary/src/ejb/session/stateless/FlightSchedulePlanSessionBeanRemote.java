/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.Remote;
import util.exception.FlightSchedulePlanNotFoundException;

/**
 *
 * @author alvintjw
 */
@Remote
public interface FlightSchedulePlanSessionBeanRemote {

    public Long createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS);

    public FlightSchedulePlan retrieveStaffByStaffId(Long fspid) throws FlightSchedulePlanNotFoundException;

    public List<FlightSchedulePlan> retrieveAllFlightSchedulePlan();
    
}
