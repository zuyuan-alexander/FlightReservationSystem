/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frsmanagementclient;

import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRouteSessionBeanRemote;
import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.CabinClass;
import entity.Employee;
import entity.FlightRoute;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.enumeration.CabinClassTypeEnum;
import util.enumeration.EmployeeTypeEnum;
import util.exception.AircraftTypeNotFoundException;
import util.exception.AirportNotFoundException;
import util.exception.FlightRouteNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author alvintjw
 */
public class MainApp {
    
    private EmployeeSessionBeanRemote employeeSessionBean;
    private AircraftSessionBeanRemote aircraftSessionBeanRemote;
    private FlightRouteSessionBeanRemote flightRouteSessionBeanRemote;
    private Employee currentEmployee;

    public MainApp(EmployeeSessionBeanRemote employeeSessionBean, AircraftSessionBeanRemote aircraftSessionBeanRemote, FlightRouteSessionBeanRemote flightRouteSessionBeanRemote) {
        this.employeeSessionBean = employeeSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.flightRouteSessionBeanRemote = flightRouteSessionBeanRemote;
    }
      
    public void runApp()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to FRS Mangagement System***\n");
            System.out.println("1: Login");
            System.out.println("2: Exit\n");
            response = 0;
            
            while(response < 1 || response > 2)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    try
                    {
                        doLogin();
                        System.out.println("Login successful!\n");
                        
                        
                      
                    }
                    catch(InvalidLoginCredentialException ex) 
                    {
                        System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                    }
                }
                else if (response == 2)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 2)
            {
                break;
            }
        }
    }
     
     private void doLogin() throws InvalidLoginCredentialException
    {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";
        
        System.out.println("*** FRS Management System :: Login ***\n");
        System.out.print("Enter username> ");
        username = scanner.nextLine().trim();
        System.out.print("Enter password> ");
        password = scanner.nextLine().trim();
        
        if(username.length() > 0 && password.length() > 0)
        {
            currentEmployee = employeeSessionBean.employeeLogin(username, password);   
            if(currentEmployee.getUserRole().equals(EmployeeTypeEnum.SCHEDULE_MANAGER))
            {
                FlightOperationModuleMenuMain();
            } else if(currentEmployee.getUserRole().equals(EmployeeTypeEnum.ROUTE_MANAGER))
            {
                RoutePlannerMenuMain();
            } else if (currentEmployee.getUserRole().equals(EmployeeTypeEnum.FLEET_MANAGER))
            {
                FleetManagerMenuMain();
            } else if (currentEmployee.getUserRole().equals(EmployeeTypeEnum.SALES_MANAGER))
            {
                SalesManagerMenuMain();
            }
        }
        else
        {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
     
    private void FlightOperationModuleMenuMain()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to FRS Schedule Manager Menu***\n");
            System.out.println("1: Create Flight");
            System.out.println("2: View All Flights");
            System.out.println("3: View Flight Details");
            System.out.println("4: Update Flight");
            System.out.println("5: Delete Flight");
            System.out.println("6: Create Flight Schedule Plan");
            System.out.println("7: View all Flight Schedule Plan");
            System.out.println("8: View Flight Schedule Plan Details");
            System.out.println("9: Update Flight Schedule Plan");
            System.out.println("10: Delete Flight Schedule Plan");
            System.out.println("11: Exit\n");
            response = 0;
            
            while(response < 1 || response > 11)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    try
                    {
                        doLogin();
                        System.out.println("Login successful!\n");
                        
                        
                      
                    }
                    catch(InvalidLoginCredentialException ex) 
                    {
                        System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                    }
                }
                else if (response == 11)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 11)
            {
                break;
            }
        }
    }
    
    
    private void RoutePlannerMenuMain()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to FRS Route Planner Menu***\n");
            System.out.println("1: Create Flight Route");
            System.out.println("2: View All Flight Routes");
            System.out.println("3: Delete Flight Route");
            System.out.println("4: Exit\n");
            response = 0;
            
            while(response < 1 || response > 4)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    doCreateFlightRoute();
                }
                else if (response == 2) 
                {
                    doViewAllFlightRoutes();
                }
                else if (response == 3) 
                {
                    doDeleteFlightRoute();
                }
                else if (response == 4)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 4)
            {
                break;
            }
        }
    }
    
    private void FleetManagerMenuMain()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to FRS Fleet Manager Menu***\n");
            System.out.println("1: Create Aircraft Configuration");
            System.out.println("2: View All Aircraft Configuration");
            System.out.println("3: View Aircraft Configuration Details");
            System.out.println("4: Exit\n");
            response = 0;
            
            while(response < 1 || response > 4)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if (response ==1 ) {
                    doCreateAircraftConfiguration();
                } else if(response == 2) {
                    doViewAllAircraftConfigurations();
                } else if(response == 3) {
                    doViewAircraftConfigurationDetails();
                } else if(response == 4) {
                    break;
                } else
                {
                    System.out.println("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 4)
            {
                break;
            }
        }
    }
    
    
    private void SalesManagerMenuMain()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to FRS Sales Manager Menu***\n");
            System.out.println("1: View Seats Inventory");
            System.out.println("2: View Flight Reservations");
            System.out.println("3: Exit\n");
            response = 0;
            
            while(response < 1 || response > 3)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    try
                    {
                        doLogin();
                        System.out.println("Login successful!\n");
                        
                        
                      
                    }
                    catch(InvalidLoginCredentialException ex) 
                    {
                        System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                    }
                }
                else if (response == 3)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 3)
            {
                break;
            }
        }
    }
    
    public void doCreateAircraftConfiguration() {
        System.out.println("====== Create Aircraft Configuration =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter aircraft type > ");
        String aircraftTypeName = scanner.nextLine().trim();
        System.out.print("Enter aircraft configuration name > ");
        String aircraftConfigurationName = scanner.nextLine().trim();
        System.out.print("Enter number of cabin classes > ");
        Integer numOfCabinClasses = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter maximum capacity > ");
        Integer maximumCapacity = scanner.nextInt();
        scanner.nextLine();
        
        List<CabinClass> cabinClassList = new ArrayList<>();
        Integer maxCapacityLimit = 0;
        
        for (int i=1; i<=numOfCabinClasses; i++) {
            System.out.println("*** Cabin Class " + i + " ***");
            System.out.print("Enter cabin class type (F: First, J: Business, W: Premium Economy, Y: Economy) > ");
            String cabinClassTypeStr = scanner.nextLine().trim();
            
            CabinClassTypeEnum cabinClassType = null;
            if (cabinClassTypeStr.equalsIgnoreCase("F")) {
                cabinClassType = CabinClassTypeEnum.F;
            } else if (cabinClassTypeStr.equalsIgnoreCase("J")) {
                cabinClassType = CabinClassTypeEnum.J;
            } else if (cabinClassTypeStr.equalsIgnoreCase("W")) {
                cabinClassType = CabinClassTypeEnum.W;
            } else if (cabinClassTypeStr.equalsIgnoreCase("Y")) {
                cabinClassType = CabinClassTypeEnum.Y;
            }
            
            System.out.print("Enter number of aisles > ");
            Integer numOfAisles = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter number of rows > ");
            Integer numOfRows = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter number of seat abreast > ");
            Integer numOfSeatAbreast = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter seat configuration > ");
            String seatConfiguration = scanner.nextLine().trim();
            System.out.print("Enter maximum capacity of the cabin class > ");
            Integer maxCapacity = scanner.nextInt();
            scanner.nextLine();
            
            maxCapacityLimit += maxCapacity;
            if (maxCapacityLimit > maximumCapacity) {
                System.out.println("Total capacity of all cabin classes exceeds the maximum capacity of the aircraft configuration!");
                return;
            }
            
            CabinClass cabinClass = new CabinClass(cabinClassType, numOfAisles, numOfRows, numOfSeatAbreast, seatConfiguration, maxCapacity);
            cabinClassList.add(cabinClass);
        }
        
        try {
            AircraftConfiguration aircraftConfiguration = new AircraftConfiguration(aircraftConfigurationName, numOfCabinClasses, maximumCapacity);
            AircraftType aircraftType = aircraftSessionBeanRemote.retrieveAircraftByAircraftTypeName(aircraftTypeName);
        
            Long aircraftConfigurationId = aircraftSessionBeanRemote.createAircraftConfiguration(aircraftConfiguration, aircraftType, cabinClassList);
            
            System.out.println("Aircraft Configuration with Id " + aircraftConfigurationId + " has been successfully created!");
        } catch(AircraftTypeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } 
    }
    
    public void doViewAllAircraftConfigurations() {
        System.out.println("====== View All Aircraft Configuration =====");
        List<AircraftConfiguration> aircraftConfigurationList = aircraftSessionBeanRemote.viewAllAircraftConfigurations();
        
        for (AircraftConfiguration ac : aircraftConfigurationList) {
            System.out.println("Aircraft Configuration Id: " + ac.getAircraftConfigurationId() + "; Name: " + ac.getAircraftConfigurationName());
        }
    }
    
    public void doViewAircraftConfigurationDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====== View Aircraft Configuration Details =====");
        System.out.print("Enter aircraft configuration id > ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        
        try {
            AircraftConfiguration ac = aircraftSessionBeanRemote.viewAircraftConfigurationDetails(id);
            System.out.println("Aircraft Configuration Id : " + ac.getAircraftConfigurationId());
            System.out.println("Aircraft Configuration Name : " + ac.getAircraftConfigurationName());
            System.out.println("Number of Cabin Classes : " + ac.getNumOfCabinClass());
            System.out.println("Maximum Capacity : " + ac.getMaximumCapacity());
        } catch (AircraftTypeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
     
    
    public void doCreateFlightRoute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====== Create Flight Route =====");
        System.out.print("Enter Origin IATA Airport Code > ");
        String origin = scanner.nextLine().trim();
        System.out.print("Enter Destination IATA Airport Code > ");
        String destination = scanner.nextLine().trim();
        
        FlightRoute flightRoute = new FlightRoute(origin, destination);
        try {
            Long id = flightRouteSessionBeanRemote.createFlightRoute(flightRoute);
            System.out.println("Flight Route with Flight Route Id " + id + " has been successfully created!");
        } catch (AirportNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
    
    public void doViewAllFlightRoutes() {
        System.out.println("====== View All Flight Routes =====");
        List<FlightRoute> flightRouteList = flightRouteSessionBeanRemote.viewAllFlightRoutes();
        
        for (FlightRoute fr : flightRouteList) {
            System.out.println("Flight Route Id: " + fr.getFlightRouteId() + " with Origin " + fr.getOrigin() + " and Destination " + fr.getDestination());
        }
    }
    
    public void doDeleteFlightRoute() {
        System.out.println("====== Delete Flight Route =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter flight route id > ");
        Long id = scanner.nextLong();
        
        try {
            flightRouteSessionBeanRemote.deleteFlightRoute(id);
            System.out.println("Flight Route with Id " + id + " has been successfully deleted!");
        } catch (FlightRouteNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
}
