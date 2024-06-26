/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Fare;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.Local;
import util.exception.FlightDisabledException;
import util.exception.FlightSchedulePlanNotFoundException;
import util.exception.InputDataValidationException;

/**
 *
 * @author alvintjw
 */
@Local
public interface FlightSchedulePlanSessionBeanLocal {

     public Long createNewRWFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS) throws InputDataValidationException, FlightDisabledException;

     public FlightSchedulePlan retrieveFSPfByFSPId(Long fspid) throws FlightSchedulePlanNotFoundException;
     public List<FlightSchedulePlan> retrieveAllFlightSchedulePlan();
     public List<FlightSchedulePlan> retrieveFlightSchedulePlanByFlightID(Long flightId);

    public FlightSchedulePlan retrieveFlightSchedulePlanByFlightNumber(String flightNumber);
     public void updateFlightSchedulePlan(List<Fare> fares, Long fspid) throws FlightDisabledException, FlightSchedulePlanNotFoundException;
     
      public void deleteFlightSchedulePlan(Long fspid) throws FlightSchedulePlanNotFoundException;
      
      public Long createCompliMentaryFlightSchedulePlan(Flight f, FlightSchedulePlan newFSP, FlightSchedule newFS, Long mainfspid) throws InputDataValidationException, FlightDisabledException;
    //public List<FlightSchedule> retrieveFlightScheduleByFSP(Long fspId) throws FlightSchedulePlanNotFoundException;

    //public List<Fare> retrieveFareByFSPId(Long fspId) throws FlightSchedulePlanNotFoundException;
    
    
}
