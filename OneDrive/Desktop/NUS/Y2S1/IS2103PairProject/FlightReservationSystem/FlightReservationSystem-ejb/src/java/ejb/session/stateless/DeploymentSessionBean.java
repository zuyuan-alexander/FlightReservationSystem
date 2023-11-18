/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClass;
import entity.Fare;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.ScheduleTypeEnum;
import util.exception.FlightNotFoundException;

/**
 *
 * @author zuyua
 */
@Stateless
public class DeploymentSessionBean implements DeploymentSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private FlightSchedulePlanSessionBeanLocal flightSchedulePlanSessionBeanLocal;
    
    @EJB
    private FlightSessionBeanLocal flightSessionBean;
    
    @EJB
    private FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    
    @EJB
    private FareSessionBeanLocal fareSessionBeanLocal;

    public DeploymentSessionBean() {
    }
    
    

    @Override
    public void deployFS() {
        // Parse dates and FlightDuration
        FlightSchedulePlan newFSP = new FlightSchedulePlan();
        FlightSchedule newFS = new FlightSchedule();
        Flight f = new Flight();
        String flightnumber = "ML111";
        
        try
        {
           f = flightSessionBean.retrieveFlightByFlightNumber(flightnumber);
        } catch (FlightNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH 'Hours' mm 'Minute'");
        SimpleDateFormat departureFormat = new SimpleDateFormat("hh:mm a");
        
        newFSP.setScheduleType(ScheduleTypeEnum.RECURRENTWEEKLY);
        //System.out.println("Enter Day Of Week> ");
        String dayOfWeek = "Monday";
        //System.out.println("Enter Departure Time> eg: 9:00 AM");
        String departureTimestr = "9:00 AM";
        //System.out.println("Enter Start Date> dd MMM yy");
        String startDateStr = "01 Dec 2023";
        //System.out.println("Enter End Date> dd MMM yy");
        String endDateStr = "30 Dec 2023";
        //System.out.println("Enter Flight Duration> HH Hours mm Minutes");
        String flightDurationStr = "14 Hours 30 Minutes";

        //set the departureTime and flightDuration of the new FS

        //call the FS sessionBean to create the FS


        // Parse dates and FlightDuration
        try
        {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            Date flightDuration = timeFormat.parse(flightDurationStr);
            Date departureTime = departureFormat.parse(departureTimestr);


            newFSP.setDayOfWeek(dayOfWeek);
            newFSP.setStartDate(startDate);
            newFSP.setEndDate(endDate);
            newFSP.setNdays(7);
            newFS.setDepartureDate(startDate);
            newFS.setDepartureTime(departureTime);
            newFS.setEstimatedFlightDuration(flightDuration);
            newFS.calculateAndSetArrivalDateTime();
            
            List<Fare> fares = new ArrayList<>();
            List<BigDecimal> fareAmountList = new ArrayList<>();
            fareAmountList.add(BigDecimal.valueOf(6000));
            fareAmountList.add(BigDecimal.valueOf(3000));
            fareAmountList.add(BigDecimal.valueOf(1000));
            Integer counter = 0;
            
            Long newfspid = flightSchedulePlanSessionBeanLocal.createNewRWFlightSchedulePlan(f, newFSP, newFS);
            
            for(CabinClass cabinClass : f.getAircraftConfiguration().getCabinClasses()) {
                Fare fare = new Fare("farebc", fareAmountList.get(counter), cabinClass.getCabinClassType());
                fareSessionBeanLocal.createNewFare(fare, newFSP);
                counter++;
            }
            
            Long newfsid = flightScheduleSessionBeanRemote.createNewFlightSchedule(newFS, newfspid);
            System.out.println("Main Date " + newFS.getDepartureDate());
            while(newFS.getDepartureDate().before(endDate))
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(newFS.getDepartureDate());
                calendar.add(Calendar.DAY_OF_MONTH, 7); // Increment by 7 days
                Date newDate = calendar.getTime();
                System.out.println("Testing date " + newDate);
                FlightSchedule fs = newFS;
                fs.setDepartureDate(newDate);
                fs.calculateAndSetArrivalDateTime(); 
                newfsid = flightScheduleSessionBeanRemote.createNewFlightSchedule(fs, newfspid);
                System.out.println("Created new fs");
            }

            //em.persist(newFSP)

        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
    }

    
}
