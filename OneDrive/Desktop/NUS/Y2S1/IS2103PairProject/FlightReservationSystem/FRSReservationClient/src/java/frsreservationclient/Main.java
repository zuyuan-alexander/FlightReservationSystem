 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package frsreservationclient;

import ejb.session.stateless.CabinClassSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.FareSessionBeanRemote;
import ejb.session.stateless.FlightReservationSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import ejb.session.stateless.SeatSessionBeanRemote;
import javax.ejb.EJB; 

/**
 *
 * @author zuyua
 */


public class Main {

    
    @EJB
    private static CustomerSessionBeanRemote customerSessionBeanRemote;
    
    @EJB
    private static FlightReservationSessionBeanRemote flightReservationSessionBeanRemote;
    
    @EJB
    private static FareSessionBeanRemote fareSessionBeanRemote;
    
    @EJB
    private static FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    
    @EJB
    private static CabinClassSessionBeanRemote cabinClassSessionBeanRemote;
    
    @EJB
    private static SeatSessionBeanRemote seatSessionBeanRemote;
    
    public static void main(String[] args) {
        MainApp mainApp = new MainApp(customerSessionBeanRemote, flightReservationSessionBeanRemote, fareSessionBeanRemote, flightScheduleSessionBeanRemote, cabinClassSessionBeanRemote, seatSessionBeanRemote);
        mainApp.runApp();
    }
    
}
