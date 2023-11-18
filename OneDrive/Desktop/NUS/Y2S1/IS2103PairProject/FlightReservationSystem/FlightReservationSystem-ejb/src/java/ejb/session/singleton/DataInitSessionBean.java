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
import entity.Customer;
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
//@Startup
public class DataInitSessionBean {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private AircraftSessionBeanLocal aircraftSessionBeanLocal;
    
    @EJB
    private FlightRouteSessionBeanLocal flightRouteSessionBeanLocal;

    public DataInitSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        initEmployee();
        initPartner();
        initAirport();
        initAircraftType();
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

        Customer customer = new Customer("Alvin", "Tor", "imalvin2@gmail.com", "97605641", "Eusoff Hall", "123456", "alvintjw","password");
        em.persist(customer);
        em.flush();
        
        FlightRoute flightRoute = new FlightRoute("SIN", "HKG");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
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

}
