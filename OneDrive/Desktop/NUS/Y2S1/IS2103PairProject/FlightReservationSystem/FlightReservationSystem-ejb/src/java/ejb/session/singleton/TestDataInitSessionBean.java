/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.AircraftSessionBeanLocal;
import ejb.session.stateless.FlightRouteSessionBeanLocal;
import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.Airport;
import entity.CabinClass;
import entity.Employee;
import entity.FlightRoute;
import entity.Partner;
import java.util.ArrayList;
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
import util.exception.AircraftTypeNotFoundException;
import util.exception.AirportNotFoundException;

/**
 *
 * @author zuyua
 */
@Singleton
@LocalBean
@Startup
public class TestDataInitSessionBean {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private AircraftSessionBeanLocal aircraftSessionBeanLocal;
    
    @EJB
    private FlightRouteSessionBeanLocal flightRouteSessionBeanLocal;

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
}
