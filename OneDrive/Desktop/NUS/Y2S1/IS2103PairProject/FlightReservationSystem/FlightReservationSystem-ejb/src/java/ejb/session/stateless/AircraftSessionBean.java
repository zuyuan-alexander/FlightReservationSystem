/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Aircraft;
import entity.AircraftConfiguration;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.AircraftTypeEnum;
import util.exception.AircraftTypeNotFoundException;

/**
 *
 * @author zuyua
 */
@Stateless
public class AircraftSessionBean implements AircraftSessionBeanRemote, AircraftSessionBeanLocal {
    
    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public AircraftSessionBean() {
    }
    
    public Long createNewAircraftType(Aircraft aircraft) {
        em.persist(aircraft);
        em.flush();
        
        return aircraft.getAircraftId();
    }
    
    public Aircraft retrieveAircraftByAircraftType(AircraftTypeEnum aircraftTypeEnum) throws AircraftTypeNotFoundException {
        Query query = em.createQuery("SELECT a FROM Aircraft a WHERE a.aircraftTypeName = :inAircraftType");
        query.setParameter("inAircraftType", aircraftTypeEnum);
        
        try
        {
            return (Aircraft) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new AircraftTypeNotFoundException("Aircraft Type " + aircraftTypeEnum + " does not exist!");
        }
    }
    /*
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfiguration, AircraftTypeEnum aircraftTypeEnum) throws AircraftTypeNotFoundException {
        Aircraft aircraft = retrieveAircraftByAircraftType(aircraftTypeEnum);
        
        
    }
    
    public List<AircraftConfiguration> viewAllAircraftConfigurations() {
        Query query = em.createQuery("SELECT ac FROM AircraftConfiguration ac ORDER BY ac.aircraft");
    }
    */
    public AircraftConfiguration viewAircraftConfigurationDetails(Long aircraftConfigurationId) throws AircraftTypeNotFoundException {
        return retrieveAircraftConfigurationById(aircraftConfigurationId);
    }
    
    public AircraftConfiguration retrieveAircraftConfigurationById(Long aircraftConfigurationId) throws AircraftTypeNotFoundException {
        AircraftConfiguration aircraftConfiguration = em.find(AircraftConfiguration.class, aircraftConfigurationId);
        
        if(aircraftConfiguration != null)
        {
            return aircraftConfiguration;
        }
        else
        {
            throw new AircraftTypeNotFoundException("Aicraft Configuration with Aircraft Cnfiguration Id " + aircraftConfigurationId + " does not exist!");
        }               
    }
    
}
