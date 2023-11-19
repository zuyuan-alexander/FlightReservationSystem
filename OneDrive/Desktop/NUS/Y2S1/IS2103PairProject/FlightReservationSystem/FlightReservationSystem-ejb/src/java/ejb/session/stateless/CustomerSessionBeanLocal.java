/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import javax.ejb.Local;
import util.exception.CustomerCredentialExistException;
import util.exception.CustomerNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zuyua
 */
@Local
public interface CustomerSessionBeanLocal {

    public Long registerAsCustomer(Customer customer) throws UnknownPersistenceException, CustomerCredentialExistException;

    public Customer customerLogin(String username, String password) throws InvalidLoginCredentialException;

    public Customer retrieveCustomerByUsername(String username) throws CustomerNotFoundException;

    public Customer retrieveCustomerByCustomerId(Long id) throws CustomerNotFoundException;
    
}
