/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Partner;
import javax.ejb.Local;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.PartnerNotFoundException;
import util.exception.PartnerUsernameExistException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author alvintjw
 */
@Local
public interface PartnerSessionBeanLocal {
    public Partner PartnerLogin(String username, String password) throws InvalidLoginCredentialException;
    public Long createNewPartner(Partner newPartner) throws PartnerUsernameExistException, UnknownPersistenceException, InputDataValidationException;
    public Partner retrievePartnerByUsername(String username) throws PartnerNotFoundException;
    
}
