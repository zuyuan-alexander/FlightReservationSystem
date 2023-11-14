 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frsreservationclient;

import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.FlightReservationSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author zuyua
 */
public class Main {

    @EJB
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    
    @EJB
    private FlightReservationSessionBeanRemote flightReservationSessionBeanRemote;
    
    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.runApp();
    }
    
}
