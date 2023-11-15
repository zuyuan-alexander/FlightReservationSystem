/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightSchedule;
import javax.ejb.Remote;
import util.exception.FlightScheduleNotFoundException;

/**
 *
 * @author alvintjw
 */
@Remote
public interface FlightScheduleSessionBeanRemote {

    public Long createNewFlightSchedule(FlightSchedule fs, Long newFSPid);

    public FlightSchedule retrieveFlightScheduleById(Long flightScheduleid) throws FlightScheduleNotFoundException;
    
}
