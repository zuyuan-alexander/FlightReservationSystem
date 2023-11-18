/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frsmanagementclient;

import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FareSessionBeanRemote;
import ejb.session.stateless.FlightSchedulePlanSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;

import ejb.session.stateless.FlightRouteSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import ejb.session.stateless.ManagementSessionBeanRemote;

import javax.ejb.EJB;

/**
 *
 * @author zuyua
 */
public class Main {

    @EJB
    private static FlightSessionBeanRemote flightSessionBean;

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
    
    @EJB
    private static ManagementSessionBeanRemote managementSessionBeanRemote;
    
    @EJB
    private static FareSessionBeanRemote fareSessionBeanRemote;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MainApp mainApp = new MainApp(employeeSessionBean,flightScheduleSessionBean, flightSchedulePlanSessionBean,aircraftSessionBeanRemote,  flightRouteSessionBeanRemote, flightSessionBean, managementSessionBeanRemote, fareSessionBeanRemote);
        mainApp.runApp();
    }
    
}
