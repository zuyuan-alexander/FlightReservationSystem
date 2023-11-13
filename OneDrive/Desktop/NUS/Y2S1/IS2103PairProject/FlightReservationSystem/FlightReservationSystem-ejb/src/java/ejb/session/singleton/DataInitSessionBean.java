/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.Airport;
import entity.Employee;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.EmployeeTypeEnum;

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

    public DataInitSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        Employee employee = new Employee("Alvin", "alvintjw", "password", EmployeeTypeEnum.SCHEDULE_MANAGER);
        em.persist(employee);
        em.flush();
        
        Airport airport = new Airport("Changi", "SIN", "Singapore", "Singapore", "Singapore");
        em.persist(airport);
        em.flush();
        airport = new Airport("Hong Kong", "HKG", "Chek Lap Kok", "Hong Kong", "China");
        em.persist(airport);
        em.flush();
        
        AircraftType aircraft = new AircraftType("Boeing 7737", 200);
        em.persist(aircraft);
        em.flush();
        
        //AircraftConfiguration aircraftConfiguration = new AircraftConfiguration();
        
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
