/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author alvintjw
 */
public class PassengerAlreadyExistsException extends Exception{

    /**
     * Creates a new instance of <code>PassengerAlreadyExistsException</code>
     * without detail message.
     */
    public PassengerAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>PassengerAlreadyExistsException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PassengerAlreadyExistsException(String msg) {
        super(msg);
    }
}
