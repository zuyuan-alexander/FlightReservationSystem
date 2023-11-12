/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClass;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zuyua
 */
@Stateless
public class CabinClassSessionBean implements CabinClassSessionBeanRemote, CabinClassSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;

    public CabinClassSessionBean() {
    }
    
    public Long createCabinClass(CabinClass cabinClass) {
        
        // create the seats
        
        em.persist(cabinClass);
        em.flush();
        
        return cabinClass.getCabinClassId();
    }
}
