/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.CabinClass;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zuyua
 */
@Stateless
public class AircraftConfigurationSessionBean implements AircraftConfigurationSessionBeanRemote, AircraftConfigurationSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public AircraftConfigurationSessionBean() {
    }
    
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfiguration, List<CabinClass> cabinClassList) {
        
        for (CabinClass cabinClass : cabinClassList) {
            em.persist(cabinClass);
        }
        
        // set association
        
        em.persist(aircraftConfiguration);
        em.flush();
        
        return aircraftConfiguration.getAircraftConfigurationId();
        
    }
}
