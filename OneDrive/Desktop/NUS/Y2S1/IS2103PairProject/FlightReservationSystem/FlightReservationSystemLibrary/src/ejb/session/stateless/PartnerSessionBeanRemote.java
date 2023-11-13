/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Partner;
import javax.ejb.Remote;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.PartnerNotFoundException;
import util.exception.PartnerUsernameExistException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author alvintjw
 */
@Remote
public interface PartnerSessionBeanRemote {

    public Partner PartnerLogin(String username, String password) throws InvalidLoginCredentialException;

    public Partner retrievePartnerByUsername(String username) throws PartnerNotFoundException;

    public Long createNewPartner(Partner newPartner) throws PartnerUsernameExistException, UnknownPersistenceException, InputDataValidationException;
    
}
