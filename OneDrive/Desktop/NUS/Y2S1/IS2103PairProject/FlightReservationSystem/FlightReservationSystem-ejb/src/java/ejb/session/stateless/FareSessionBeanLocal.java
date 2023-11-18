/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClass;
import entity.Fare;
import entity.FlightSchedulePlan;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zuyua
 */
@Local
public interface FareSessionBeanLocal {
    
    public Long createNewFare(Fare fare,  Long newFSPid);
    
    public BigDecimal retrieveFareAmountByCabinClassType(List<Fare> fares, CabinClass cabinClass);
    
}
