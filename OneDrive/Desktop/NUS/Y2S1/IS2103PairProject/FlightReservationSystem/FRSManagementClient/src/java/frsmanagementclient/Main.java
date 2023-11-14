/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frsmanagementclient;

import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
<<<<<<< HEAD
import ejb.session.stateless.FlightSchedulePlanSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
=======
import ejb.session.stateless.FlightRouteSessionBeanRemote;
>>>>>>> 2d8e572853d92306da38e238f104c97a5955672b
import javax.ejb.EJB;

/**
 *
 * @author zuyua
 */
public class Main {

    @EJB
    private static FlightScheduleSessionBeanRemote flightScheduleSessionBean;

    @EJB
    private static FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBean;

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

        MainApp mainApp = new MainApp(employeeSessionBean,flightScheduleSessionBean, flightSchedulePlanSessionBean,aircraftSessionBeanRemote,  flightRouteSessionBeanRemote);
        mainApp.runApp();
    }
    
}
