/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftType;
import entity.AircraftConfiguration;
import entity.CabinClass;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.AircraftTypeEnum;
import util.exception.AircraftConfigurationNotFoundException;
import util.exception.AircraftTypeNotFoundException;

/**
 *
 * @author zuyua
 */
@Stateless
public class AircraftSessionBean implements AircraftSessionBeanRemote, AircraftSessionBeanLocal {
    
    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private CabinClassSessionBeanLocal cabinClassSessionBeanLocal;

    public AircraftSessionBean() {
    }
    
    @Override
    public Long createNewAircraftType(AircraftType aircraft) {
        em.persist(aircraft);
        em.flush();
        
        return aircraft.getAircraftTypeId();
    }
    
    @Override
    public AircraftType retrieveAircraftByAircraftTypeName(String aircraftTypeName) throws AircraftTypeNotFoundException {
        Query query = em.createQuery("SELECT at FROM AircraftType at WHERE at.aircraftTypeName = :inAircraftType");
        query.setParameter("inAircraftType", aircraftTypeName);
        
        try
        {
            return (AircraftType) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new AircraftTypeNotFoundException("Aircraft Type " + aircraftTypeName + " does not exist!");
        }
    }
    
    @Override
    public AircraftType retrieveAircraftByAircraftTypeId(Long aircraftTypeId) throws AircraftTypeNotFoundException {
        AircraftType aicraftType = em.find(AircraftType.class, aircraftTypeId);
        
        if(aicraftType != null)
        {
            return aicraftType;
        }
        else
        {
            throw new AircraftTypeNotFoundException("Aicraft Configuration with Aircraft Cnfiguration Id " + aircraftTypeId + " does not exist!");
        } 
    }
    
    @Override
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfiguration, AircraftType aircraftType, List<CabinClass> cabinClassList) throws AircraftTypeNotFoundException {
        AircraftType aircraft = retrieveAircraftByAircraftTypeName(aircraftType.getAircraftTypeName());
        
        aircraftConfiguration.setAircraftType(aircraftType);
        
        em.persist(aircraftConfiguration);
        em.flush();
        
        for(CabinClass cabinClass : cabinClassList) {
            aircraftConfiguration.getCabinClasses().add(cabinClass);
            cabinClass.setAircraftConfiguration(aircraftConfiguration);
            
            cabinClassSessionBeanLocal.createCabinClass(cabinClass);
        }
        
        return aircraftConfiguration.getAircraftConfigurationId();
    }
    
    @Override
    public List<AircraftConfiguration> viewAllAircraftConfigurations() {
        Query query = em.createQuery("SELECT ac FROM AircraftConfiguration ac ORDER BY ac.aircraftType ASC, ac.aircraftConfigurationName ASC");
        return query.getResultList();
    }
    
    public AircraftConfiguration viewAircraftConfigurationDetails(Long aircraftConfigurationId) throws AircraftTypeNotFoundException {
        // the details (each attribute) is printed in the client
        return retrieveAircraftConfigurationById(aircraftConfigurationId);
    }
    
    @Override
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
    
    @Override
    public AircraftConfiguration retrieveAircraftConfigurationByName(String name) throws AircraftConfigurationNotFoundException {
        Query query = em.createQuery("SELECT ac FROM AircraftConfiguration ac WHERE ac.aircraftConfigurationName = :inAircraftConfigurationName");
        query.setParameter("inAircraftConfigurationName", name);
        
        try
        {
            return (AircraftConfiguration) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new AircraftConfigurationNotFoundException("Aircraft Configuration Name " + name + " does not exist!");
        }
    }
    
}
