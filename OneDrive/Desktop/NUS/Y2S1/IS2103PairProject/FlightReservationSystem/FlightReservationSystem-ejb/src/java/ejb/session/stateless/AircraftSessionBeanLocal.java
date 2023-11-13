/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.CabinClass;
import java.util.List;
import javax.ejb.Local;
import util.exception.AircraftTypeNotFoundException;

/**
 *
 * @author zuyua
 */
@Local
public interface AircraftSessionBeanLocal {

    public Long createNewAircraftType(AircraftType aircraft);

    public AircraftType retrieveAircraftByAircraftTypeName(AircraftType aircraftType) throws AircraftTypeNotFoundException;

    public AircraftType retrieveAircraftByAircraftTypeId(Long aircraftTypeId) throws AircraftTypeNotFoundException;

    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfiguration, AircraftType aircraftType, List<CabinClass> cabinClassList) throws AircraftTypeNotFoundException;

    public List<AircraftConfiguration> viewAllAircraftConfigurations();

    public AircraftConfiguration viewAircraftConfigurationDetails(Long aircraftConfigurationId) throws AircraftTypeNotFoundException;

    public AircraftConfiguration retrieveAircraftConfigurationById(Long aircraftConfigurationId) throws AircraftTypeNotFoundException;
    
}
