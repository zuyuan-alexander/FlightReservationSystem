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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Employee employee = new Employee("Alvin", "alvintjw", "password", EmployeeTypeEnum.SCHEDULE_MANAGER);
        em.persist(employee);
        em.flush();
        
        employee = new Employee("Employee2", "employee2", "password", EmployeeTypeEnum.ROUTE_MANAGER);
        em.persist(employee);
        em.flush();
        
        employee = new Employee("Employee3", "employee3", "password", EmployeeTypeEnum.FLEET_MANAGER);
        em.persist(employee);
        em.flush();
        
        Airport airport = new Airport("Changi", "SIN", "Singapore", "Singapore", "Singapore");
        em.persist(airport);
        em.flush();
        airport = new Airport("Hong Kong", "HKG", "Chek Lap Kok", "Hong Kong", "China");
        em.persist(airport);
        em.flush();
        
        FlightRoute flightRoute = new FlightRoute("SIN", "HKG");
        flightRoute.setReturnFlight(Boolean.TRUE);
        try {
            flightRouteSessionBeanLocal.createFlightRoute(flightRoute);
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        AircraftType aircraft = new AircraftType("Boeing 737", 200);
        em.persist(aircraft);
        em.flush();
        
        List<CabinClass> cabinClassList = new ArrayList<>();
        AircraftConfiguration aircraftConfiguration = new AircraftConfiguration("Boeing 737 Three Classes", 3, 180);
        CabinClass class1 = new CabinClass(CabinClassTypeEnum.F, 1, 5, 2, "1-1", 10);
        CabinClass class2 = new CabinClass(CabinClassTypeEnum.J, 1, 5, 4, "2-2", 20);
        CabinClass class3 = new CabinClass(CabinClassTypeEnum.Y, 1, 25, 6, "3-3", 150);
        cabinClassList.add(class1);
        cabinClassList.add(class2);
        cabinClassList.add(class3);
        try {
            aircraftSessionBeanLocal.createAircraftConfiguration(aircraftConfiguration, aircraft, cabinClassList);
        } catch (AircraftTypeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
    }

    /*
    @EJB
    private PartnerSessionBeanLocal partnerSessionBean;

    @EJB
    private EmployeeSessionBeanLocal employeeSessionBean;
    
    

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public DataInitSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        try
        {
            partnerSessionBean.createNewPartner(new Partner("Alvin", "alvintjw", "password"));
             employeeSessionBean.createNewEmployee(new Employee("Alvin", "alvintjw", "password", EmployeeTypeEnum.SYSTEM_ADMINISTRATOR));
        } catch (EmployeeUsernameExistException | PartnerUsernameExistException |UnknownPersistenceException | InputDataValidationException ex)
        {
            
        }
       
        //Employee employee = new Employee("Alvin", "alvintjw", "password", EmployeeTypeEnum.SYSTEM_ADMINISTRATOR);
        //em.persist(employee);
        //em.flush();
    }
    */
}
