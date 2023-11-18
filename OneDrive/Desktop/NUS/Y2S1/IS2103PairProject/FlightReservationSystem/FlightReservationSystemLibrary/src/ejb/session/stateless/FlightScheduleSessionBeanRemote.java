/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClass;
import entity.FlightSchedule;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import util.enumeration.CabinClassTypeEnum;
import util.exception.FlightScheduleNotFoundException;

/**
 *
 * @author alvintjw
 */
@Remote
public interface FlightScheduleSessionBeanRemote {

    public Long createNewFlightSchedule(FlightSchedule fs, Long newFSPid);

    public FlightSchedule retrieveFlightScheduleById(Long flightScheduleid) throws FlightScheduleNotFoundException;
    
    public List<FlightSchedule> checkFlightScheduleWithPreferedCabinClass(List<FlightSchedule> fsList, CabinClassTypeEnum cabinClassType);
    
    public List<FlightSchedule> retrieveFlightScheduleByDate(Date date);
}
