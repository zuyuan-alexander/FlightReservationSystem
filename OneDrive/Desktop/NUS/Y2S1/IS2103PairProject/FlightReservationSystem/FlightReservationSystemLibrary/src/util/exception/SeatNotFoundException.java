/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author alvintjw
 */
public class SeatNotFoundException extends Exception{

    /**
     * Creates a new instance of <code>SeatNotFoundException</code> without
     * detail message.
     */
    public SeatNotFoundException() {
    }

    /**
     * Constructs an instance of <code>SeatNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SeatNotFoundException(String msg) {
        super(msg);
    }
}
