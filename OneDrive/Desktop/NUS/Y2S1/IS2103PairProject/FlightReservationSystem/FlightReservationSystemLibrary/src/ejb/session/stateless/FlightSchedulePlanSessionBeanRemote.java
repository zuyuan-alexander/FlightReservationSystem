/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import javax.ejb.Remote;

/**
 *
 * @author alvintjw
 */
@Remote
public interface FlightSchedulePlanSessionBeanRemote {

    public void createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS);
    
}
