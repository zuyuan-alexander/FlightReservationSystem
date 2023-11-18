/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.CabinClass;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AircraftConfigurationNotFoundException;
import util.exception.AircraftTypeNotFoundException;

/**
 *
 * @author zuyua
 */
@Remote
public interface AircraftSessionBeanRemote {
    
    public Long createNewAircraftType(AircraftType aircraft);

    public AircraftType retrieveAircraftByAircraftTypeName(String aircraftTypeName) throws AircraftTypeNotFoundException;

    public AircraftType retrieveAircraftByAircraftTypeId(Long aircraftTypeId) throws AircraftTypeNotFoundException;

    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfiguration, AircraftType aircraftType, List<CabinClass> cabinClassList) throws AircraftTypeNotFoundException;

    public List<AircraftConfiguration> viewAllAircraftConfigurations();

    public AircraftConfiguration retrieveAircraftConfigurationById(Long aircraftConfigurationId) throws AircraftConfigurationNotFoundException;
    
    public AircraftConfiguration retrieveAircraftConfigurationByName(String name) throws AircraftConfigurationNotFoundException;
    
}
