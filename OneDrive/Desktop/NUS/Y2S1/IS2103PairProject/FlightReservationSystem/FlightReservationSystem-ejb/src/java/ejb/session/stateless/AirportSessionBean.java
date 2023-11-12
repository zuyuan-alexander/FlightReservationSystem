/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AirportNotFoundException;

/**
 *
 * @author zuyua
 */
@Stateless
public class AirportSessionBean implements AirportSessionBeanRemote, AirportSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public AirportSessionBean() {
    }

    @Override
    public Long createNewAirport(Airport airport) {
        em.persist(airport);
        em.flush();
        
        return airport.getAirportId();
    }
    
    @Override
    public Airport retrieveAirportByAirportId(Long airportId) throws AirportNotFoundException {
        Airport airport = em.find(Airport.class, airportId);
        
        if (airport != null) {
            return airport;
        } else {
            throw new AirportNotFoundException("Airport with Airport Id " + airportId + " does not exist!");
        }
    }
    
    @Override
    public Airport retrieveAirportByIATACode(String iataAirportCode) throws AirportNotFoundException {
        Query query = em.createQuery("SELECT a FROM Airport a WHERE a.iataAirportCode = :inIATACode");
        query.setParameter("inIATACode", iataAirportCode);
        
        try
        {
            return (Airport) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new AirportNotFoundException("Airport with IATA Code " + iataAirportCode + " does not exist!");
        }
    }

    
}
