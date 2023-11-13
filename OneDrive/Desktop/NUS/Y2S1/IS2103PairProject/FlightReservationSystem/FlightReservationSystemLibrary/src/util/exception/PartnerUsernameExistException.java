/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author alvintjw
 */
public class PartnerUsernameExistException extends Exception{

    /**
     * Creates a new instance of <code>PartnerUsernameExistException</code>
     * without detail message.
     */
    public PartnerUsernameExistException() {
    }

    /**
     * Constructs an instance of <code>PartnerUsernameExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PartnerUsernameExistException(String msg) {
        super(msg);
    }
}
