/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.AircraftSessionBeanLocal;
import ejb.session.stateless.FareSessionBeanLocal;
import ejb.session.stateless.FlightRouteSessionBeanLocal;
import ejb.session.stateless.FlightSchedulePlanSessionBeanLocal;
import ejb.session.stateless.FlightScheduleSessionBeanLocal;
import ejb.session.stateless.FlightSessionBeanLocal;
import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.Airport;
import entity.CabinClass;
import entity.Employee;
import entity.Fare;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.Partner;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.CabinClassTypeEnum;
import util.enumeration.EmployeeTypeEnum;
import util.enumeration.ScheduleTypeEnum;
import util.exception.AircraftConfigurationNotFoundException;
import util.exception.AircraftTypeNotFoundException;
import util.exception.AirportNotFoundException;
import util.exception.FlightNotFoundException;
import util.exception.FlightNumberExistsException;
import util.exception.FlightRouteNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateFlightException;

/**
 *
 * @author zuyua
 */
@Singleton
@LocalBean
@Startup
public class TestDataInitSessionBean {

    @EJB
    private FlightSessionBeanLocal flightSessionBean;

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private AircraftSessionBeanLocal aircraftSessionBeanLocal;
    
    @EJB
    private FlightRouteSessionBeanLocal flightRouteSessionBeanLocal;
    
    @EJB
    private FlightSchedulePlanSessionBeanLocal flightSchedulePlanSessionBeanLocal;
    
    @EJB
    private FlightScheduleSessionBeanLocal flightScheduleSessionBeanLocal;
    
    @EJB
    private FareSessionBeanLocal fareSessionBeanLocal;
    
    
    public TestDataInitSessionBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        initEmployee();
        initPartner();
        initAirport();
        initAircraftType();
        initAircraftConfiguration();
        initFlightRoute();
        initFlight();
        initFlightSchedulePlan();
    }
    
    public void initEmployee() {
        Employee employee = new Employee("Fleet Manager", "fleetmanager", "password", EmployeeTypeEnum.FLEET_MANAGER);
        em.persist(employee);
        em.flush();
        
        employee = new Employee("Route Planner", "routeplanner", "password", EmployeeTypeEnum.ROUTE_MANAGER);
        em.persist(employee);
        em.flush();
        
        employee = new Employee("Schedule Manager", "schedulemanager", "password", EmployeeTypeEnum.SCHEDULE_MANAGER);
        em.persist(employee);
        em.flush();
        
        employee = new Employee("Sales Manager", "salesmanager", "password", EmployeeTypeEnum.SALES_MANAGER);
        em.persist(employee);
        em.flush();
    }
    
    public void initPartner() {
        Partner partner = new Partner("Holiday.com", "holidaydotcom", "password");
        em.persist(partner);
        em.flush();
    }
    
    public void initAirport() {
        Airport airport = new Airport("Changi", "SIN", "Singapore", "Singapore", "Singapore");
        em.persist(airport);
        em.flush();
        
        airport = new Airport("Hong Kong", "HKG", "Chek Lap Kok", "Hong Kong", "China");
        em.persist(airport);
        em.flush();
        
        airport = new Airport("Taoyuan", "TPE", "Taoyuan", "Taipei", "Taiwan R.O.C");
        em.persist(airport);
        em.flush();
        
        airport = new Airport("Narita", "NRT", "Narita", "Chiba", "Japan");
        em.persist(airport);
        em.flush();
        
        airport = new Airport("Sydney", "SYD", "Sydney", "New South Wales", "Australia");
        em.persist(airport);
        em.flush();
    }
    
    public void initAircraftType() {
        AircraftType aircraft = new AircraftType("Boeing 737", 200);
        em.persist(aircraft);
        em.flush();
        
        aircraft = new AircraftType("Boeing 747", 400);
        em.persist(aircraft);
        em.flush();
    }
    
    public void initAircraftConfiguration() {
        // Boeing 737 All Economy
        List<CabinClass> cabinClassList = new ArrayList<>();
        AircraftConfiguration aircraftConfiguration = new AircraftConfiguration("Boeing 737 All Economy", 1, 180);
        CabinClass cabinClass = new CabinClass(CabinClassTypeEnum.Y, 1, 30, 6, "3-3", 180);
        cabinClassList.add(cabinClass);
        try {
            AircraftType aircraft = aircraftSessionBeanLocal.retrieveAircraftByAircraftTypeName("Boeing 737");
            aircraftSessionBeanLocal.createAircraftConfiguration(aircraftConfiguration, aircraft, cabinClassList);
        } catch (AircraftTypeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        // Boeing 737 Three Classes
        cabinClassList = new ArrayList<>();
        aircraftConfiguration = new AircraftConfiguration("Boeing 737 Three Classes", 3, 180);
        CabinClass class1 = new CabinClass(CabinClassTypeEnum.F, 1, 5, 2, "1-1", 10);
        CabinClass class2 = new CabinClass(CabinClassTypeEnum.J, 1, 5, 4, "2-2", 20);
        CabinClass class3 = new CabinClass(CabinClassTypeEnum.Y, 1, 25, 6, "3-3", 150);
        cabinClassList.add(class1);
        cabinClassList.add(class2);
        cabinClassList.add(class3);
        try {
            AircraftType aircraft = aircraftSessionBeanLocal.retrieveAircraftByAircraftTypeName("Boeing 737");
            aircraftSessionBeanLocal.createAircraftConfiguration(aircraftConfiguration, aircraft, cabinClassList);
        } catch (AircraftTypeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        // Boeing 747 All Economy
        cabinClassList = new ArrayList<>();
        aircraftConfiguration = new AircraftConfiguration("Boeing 747 All Economy", 1, 380);
        cabinClass = new CabinClass(CabinClassTypeEnum.Y, 2, 38, 10, "3-4-3", 380);
        cabinClassList.add(cabinClass);
        try {
            AircraftType aircraft = aircraftSessionBeanLocal.retrieveAircraftByAircraftTypeName("Boeing 747");
            aircraftSessionBeanLocal.createAircraftConfiguration(aircraftConfiguration, aircraft, cabinClassList);
        } catch (AircraftTypeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        // Boeing 747 Three Classes
        cabinClassList = new ArrayList<>();
        aircraftConfiguration = new AircraftConfiguration("Boeing 747 Three Classes", 3, 360);
        class1 = new CabinClass(CabinClassTypeEnum.F, 1, 5, 2, "1-1", 10);
        class2 = new CabinClass(CabinClassTypeEnum.J, 2, 5, 6, "2-2-2", 30);
        class3 = new CabinClass(CabinClassTypeEnum.Y, 2, 32, 10, "3-4-3", 320);
        cabinClassList.add(class1);
        cabinClassList.add(class2);
        cabinClassList.add(class3);
        try {
            AircraftType aircraft = aircraftSessionBeanLocal.retrieveAircraftByAircraftTypeName("Boeing 747");
            aircraftSessionBeanLocal.createAircraftConfiguration(aircraftConfiguration, aircraft, cabinClassList);
        } catch (AircraftTypeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
    }
    
    public void initFlightRoute() {
        // SIN-HKG
        FlightRoute flightRoute = new FlightRoute("SIN", "HKG");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        //SIN-TPE
        flightRoute = new FlightRoute("SIN", "TPE");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        //SIN-NRT
        flightRoute = new FlightRoute("SIN", "NRT");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        //HKG-NRT
        flightRoute = new FlightRoute("HKG", "NRT");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        // TPE-NRT
        flightRoute = new FlightRoute("TPE", "NRT");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        // SIN-SYD
        flightRoute = new FlightRoute("SIN", "SYD");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        // SYD-NRT
        flightRoute = new FlightRoute("SYD", "NRT");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
    
    public void initFlight() {
        try {
            Flight flight = new Flight("ML111");
            FlightRoute flightRoute = flightRouteSessionBeanLocal.retrieveFlightRouteByOriginDestination("SIN", "HKG");
            AircraftConfiguration acn = aircraftSessionBeanLocal.retrieveAircraftConfigurationByName("Boeing 737 Three Classes");
            flight.setFlightRoute(flightRoute);
            flight.setAircraftConfiguration(acn);
            
            flightSessionBean.createNewFlight(flight);
            
            flightSessionBean.createComplementaryFlight(flight, "ML112");
            
        } catch (FlightRouteNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (AircraftConfigurationNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (FlightNumberExistsException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (UnknownPersistenceException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (InputDataValidationException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (FlightNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (UpdateFlightException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
    
    public void initFlightSchedulePlan() {
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
            Long newfsid = flightScheduleSessionBeanLocal.createNewFlightSchedule(newFS, newfspid);
            while(newFS.getDepartureDate().before(endDate))
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(newFS.getDepartureDate());
                calendar.add(Calendar.DAY_OF_MONTH, 7); // Increment by 7 days
                newFS.setDepartureDate(calendar.getTime());
                newFS.calculateAndSetArrivalDateTime(); 
                newfsid = flightScheduleSessionBeanLocal.createNewFlightSchedule(newFS, newfspid);
            }
            
            for(CabinClass cabinClass : f.getAircraftConfiguration().getCabinClasses()) {
                Fare fare = new Fare("farebc", fareAmountList.get(counter), cabinClass.getCabinClassType());
                fareSessionBeanLocal.createNewFare(fare, newFSP);
                counter++;
            }


            //em.persist(newFSP)

        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
    }
}
