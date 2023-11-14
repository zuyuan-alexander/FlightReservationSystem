/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frsmanagementclient;

import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRouteSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author zuyua
 */
public class Main {

    @EJB
    private static EmployeeSessionBeanRemote employeeSessionBean;
    
    @EJB
    private static AircraftSessionBeanRemote aircraftSessionBeanRemote;
    
    @EJB
    private static FlightRouteSessionBeanRemote flightRouteSessionBeanRemote;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainApp mainApp = new MainApp(employeeSessionBean, aircraftSessionBeanRemote, flightRouteSessionBeanRemote);
        mainApp.runApp();
    }
    
}
