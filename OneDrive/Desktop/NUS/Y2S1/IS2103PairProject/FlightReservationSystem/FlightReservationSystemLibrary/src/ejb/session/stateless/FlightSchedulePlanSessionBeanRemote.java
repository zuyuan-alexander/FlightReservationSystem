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
import util.exception.FlightDisabledException;
import util.exception.FlightSchedulePlanNotFoundException;
import util.exception.InputDataValidationException;

/**
 *
 * @author alvintjw
 */
@Remote
public interface FlightSchedulePlanSessionBeanRemote {


    public Long createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS) throws InputDataValidationException, FlightDisabledException;

   public FlightSchedulePlan retrieveFSPfByFSPId(Long fspid) throws FlightSchedulePlanNotFoundException;

    public List<FlightSchedulePlan> retrieveAllFlightSchedulePlan();

    public List<FlightSchedulePlan> retrieveFlightSchedulePlanByFlightID(Long flightId);
    
    public FlightSchedulePlan retrieveFlightSchedulePlanByFlightNumber(String flightNumber);

    //public List<FlightSchedule> retrieveFlightScheduleByFSP(Long fspId) throws FlightSchedulePlanNotFoundException;
    
    //public List<Fare> retrieveFareByFSPId(Long fspId) throws FlightSchedulePlanNotFoundException;
    
}
