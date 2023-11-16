/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClass;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zuyua
 */
@Local
public interface CabinClassSessionBeanLocal {
    
    public CabinClass createCabinClass(CabinClass cabinClass);
/*
    public Integer calculateNumOfReservedSeats(CabinClass cabinClass);

    public Integer calculateNumOfAvailabeSeats(CabinClass cabinClass);

    public List<Integer> calculateNumOfSeats(CabinClass cabinClass);
  */  
}
