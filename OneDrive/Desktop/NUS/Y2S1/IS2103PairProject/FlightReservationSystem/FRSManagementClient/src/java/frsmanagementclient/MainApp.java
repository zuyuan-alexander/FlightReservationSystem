/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frsmanagementclient;

import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightSchedulePlanSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import entity.FlightSchedulePlan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ejb.session.stateless.FlightRouteSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.CabinClass;
import entity.Employee;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumeration.CabinClassTypeEnum;
import util.enumeration.EmployeeTypeEnum;
import util.enumeration.ScheduleTypeEnum;
import util.exception.AircraftConfigurationNotFoundException;
import util.exception.AircraftTypeNotFoundException;
import util.exception.AirportNotFoundException;
import util.exception.FlightNotFoundException;
import util.exception.FlightNumberExistsException;
import util.exception.FlightRouteNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateFlightException;

/**
 *
 * @author alvintjw
 */
public class MainApp {
   private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    private AircraftSessionBeanRemote aircraftSessionBeanRemote;
    private EmployeeSessionBeanRemote employeeSessionBean;
    private FlightSessionBeanRemote flightSessionBean;
    private FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBean;
    private FlightScheduleSessionBeanRemote flightScheduleSessionBean;
    private FlightRouteSessionBeanRemote flightRouteSessionBeanRemote;
    private Employee currentEmployee;

    
    public MainApp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    

    public MainApp(EmployeeSessionBeanRemote employeeSessionBean, FlightScheduleSessionBeanRemote flightScheduleSessionBean, FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBean, AircraftSessionBeanRemote aircraftSessionBeanRemote, FlightRouteSessionBeanRemote flightRouteSessionBeanRemote, FlightSessionBeanRemote flightSessionBean) {
        this();
        this.employeeSessionBean = employeeSessionBean;
        this.flightSchedulePlanSessionBean = flightSchedulePlanSessionBean;
        this.flightScheduleSessionBean = flightScheduleSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.flightRouteSessionBeanRemote = flightRouteSessionBeanRemote;
        this.flightSessionBean = flightSessionBean;

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
                    doCreateFlight();
                } else if(response == 2)
                {
                    doViewAllFlights();
                } else if(response == 3)
                {
                    doViewFlightDetails();
                } else if(response == 4)
                {
                    doUpdateFlight();
                } else if(response == 5)
                {
                    doDeleteFlight();
                } else if(response == 6)
                {
                    doCreateFlightSchedulePlan();
                } else if(response == 7)
                {
                    doViewAllFlightSchedulePlan();
                } else if(response == 8)
                {
                    //doViewFlightSchedules();
                    //doViewFlightSchedulePlanDetails();
                } else if(response == 9)
                {
                    //doUpdateFlightSchedulePlan();
                } else if(response == 10)
                {
                    //doDeleteFlightSchedulePlan();
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
    /*
    private void doViewFlightSchedules()
    {
        Flight f = new Flight();
        FlightSchedulePlan fsp1 = new FlightSchedulePlan();
        FlightSchedulePlan fsp2 = new FlightSchedulePlan();
        Scanner sc = new Scanner(System.in);
        System.out.println("*** FRS Schedule Manager Menu: View All Flight Schedule Plan ***\n");
        System.out.println("Enter Flight Number> ");
        String flightnumber = sc.nextLine();
        try
        {      
            f = flightSessionBean.retrieveFlightByFlightNumber(flightnumber);
            f.getFlightscheduleplans().size();
        } catch (FlightNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        fsp1 = f.getFlightscheduleplans().get(0);
        fsp2 = f.getFlightscheduleplans().get(1);
        System.out.println("fsp1: " + fsp1.getFlightscheduleplanid()+ fsp1.getDayOfWeek());
        System.out.println("fsp2: " + fsp2.getFlightscheduleplanid()+ fsp2.getDayOfWeek());
        System.out.println("fsp1: " + fsp1.getFlightscheduleplanid() + fsp1.getFlightschedules().get(0).getFlightscheduleid());
        System.out.println("fsp2: " + fsp2.getFlightscheduleplanid() + fsp2.getFlightschedules().get(0).getFlightscheduleid());
                
    }*/
    
    
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
    
    
    private void doCreateFlight()
    {        
        Scanner sc = new Scanner(System.in);
        System.out.println("===== FRS Schedule Manager Menu: Create New Flight =====\n");
        System.out.print("Enter flight number > ");
        String flightNumber = sc.nextLine().trim();
        System.out.print("Enter origin IATA Airport Code > ");
        String origin = sc.nextLine().trim();
        System.out.print("Enter destination IATA Airport Code > ");
        String destination = sc.nextLine().trim();
        System.out.print("Enter aircraft configuration name > ");
        String aircraftConfigurationName = sc.nextLine().trim();
        System.out.print("Complimentary Flight (Y:Yes, N:No) > ");
        String response = sc.nextLine().trim();
        
        try {
            Flight flight = new Flight(flightNumber);
            FlightRoute flightRoute = flightRouteSessionBeanRemote.retrieveFlightRouteByOriginDestination(origin, destination);
            AircraftConfiguration acn = aircraftSessionBeanRemote.retrieveAircraftConfigurationByName(aircraftConfigurationName);
            flight.setFlightRoute(flightRoute);
            flight.setAircraftConfiguration(acn);
            
            Long flightId = flightSessionBean.createNewFlight(flight);
            
            if (response.equalsIgnoreCase("Y")) {
                System.out.print("Enter complementary flight number > ");
                String cFlightNumber = sc.nextLine().trim();
                
                Flight f = flightSessionBean.retrieveFlightByFlightId(flightId);
                
                Long cFlightId = flightSessionBean.createComplementaryFlight(f, cFlightNumber);
                
                System.out.println("Flight with Flight Id " + flightId + " has been successfully created!");
                System.out.println("Flight with Flight Id " + cFlightId + " has been successfully created!");
            } else {
                System.out.println("Flight with Flight Id " + flightId + " has been successfully created!");
            }
            
        } catch (FlightNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (FlightRouteNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (AircraftConfigurationNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (FlightNumberExistsException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (UnknownPersistenceException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (InputDataValidationException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (UpdateFlightException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        /*
        try
        {
            Flight flight = new Flight(flightNumber);
            FlightRoute flightRoute = flightRouteSessionBeanRemote.retrieveFlightRouteByOriginDestination(origin, destination);
            AircraftConfiguration acn = aircraftSessionBeanRemote.retrieveAircraftConfigurationByName(aircraftConfigurationName);
            
            flight.setFlightRoute(flightRoute);
            flight.setAircraftConfiguration(acn);

            Long flightId = flightSessionBean.createNewFlight(flight);
            
            //setting the complimentary flight 
            System.out.print("Complimentary Flight (Y:Yes, N:No) > ");
            String response = sc.nextLine().trim();

            if(response.equals("Y"))
            {
                System.out.print("Enter Complimentary Flight Number > ");
                String cfFlightNum = sc.nextLine().trim();
                Long complementaryFlightId = flightSessionBean.createComplementaryFlight(flight, cfFlightNum);

                System.out.println("Flight with Flight Id " + flight.getFlightId() + " has been successfully created");
                System.out.println("Flight with Flight Id " + complementaryFlightId + " has been successfully created");
            } else {
                System.out.println("Flight with Flight Id " + flight.getFlightId() + " has been successfully created");
            }
            
        } catch (FlightNumberExistsException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (FlightRouteNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (AircraftConfigurationNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (UnknownPersistenceException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (InputDataValidationException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        */
        
    } 
    
    
    public void doViewAllFlights() {
        System.out.println("===== View All Flights =====");
        List<Flight> flightList = flightSessionBean.viewAllFlight();
        for (Flight flight : flightList) {
            System.out.println("Flight Id: " + flight.getFlightId() + "; Flight Number: " + flight.getFlightNumber());
        }
    }
    
    public void doViewFlightDetails() {
        System.out.println("===== View Flight Details =====");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter flight number > ");
        String flightNumber = sc.nextLine().trim();
       try {
           Flight flight = flightSessionBean.retrieveFlightByFlightNumber(flightNumber);
           System.out.println("Flight Id: " + flight.getFlightId());
           System.out.println("Flight Number: " + flight.getFlightNumber());
           System.out.println("Flight Route: " + flight.getFlightRoute().toString());
           System.out.println("Aircraft Configuration: " + flight.getAircraftConfiguration().getAircraftConfigurationName());
       } catch (FlightNotFoundException ex) {
           System.out.println(ex.getMessage() + "\n");
       }
    }
    
    public void doUpdateFlight() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== FRS Schedule Manager Menu: Update Flight =====\n");
        System.out.print("Enter flight number > ");
        String flightNumber = sc.nextLine().trim();
        System.out.print("Enter origin IATA Airport Code > ");
        String origin = sc.nextLine().trim();
        System.out.print("Enter destination IATA Airport Code > ");
        String destination = sc.nextLine().trim();
        System.out.print("Enter aircraft configuration name > ");
        String aircraftConfigurationName = sc.nextLine().trim();
        
        try {
            Flight flight = flightSessionBean.retrieveFlightByFlightNumber(flightNumber);
            FlightRoute flightRoute = flightRouteSessionBeanRemote.retrieveFlightRouteByOriginDestination(origin, destination);
            AircraftConfiguration acn = aircraftSessionBeanRemote.retrieveAircraftConfigurationByName(aircraftConfigurationName);
            flight.setFlightRoute(flightRoute);
            flight.setAircraftConfiguration(acn);

            Long flightToUpdateId = flightSessionBean.updateFlight(flight);
            
            System.out.println("Flight with Flight Id " + flightToUpdateId + " has been successfully updated!");
        } catch (FlightNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (InputDataValidationException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (UpdateFlightException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (FlightRouteNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (AircraftConfigurationNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        } 
    }
    
    public void doDeleteFlight() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== FRS Schedule Manager Menu: Delete Flight =====\n");
        System.out.print("Enter flight id > ");
        Long flightId = sc.nextLong();
        
       try {
           flightSessionBean.deleteFlight(flightId);
           System.out.println("Flight with Flight Id " + flightId + " has been deleted!");
       } catch (FlightNotFoundException ex) {
           System.out.println(ex.getMessage() + "\n");
       }
    }
    
    private void doCreateFlightSchedulePlan()
    {
        FlightSchedulePlan newFSP = new FlightSchedulePlan();
        FlightSchedule newFS = new FlightSchedule();
        Flight f = new Flight();
        Scanner sc = new Scanner(System.in);
        System.out.println("*** FRS Schedule Manager Menu: Create New Flight Schedule Plan ***\n");
        System.out.println("Enter Flight Number> ");  
        String flightnumber = sc.nextLine();
        
        try
        {
           f = flightSessionBean.retrieveFlightByFlightNumber(flightnumber);
        } catch (FlightNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        System.out.println("Select Flight SchedulePlanType (1: Single, 2: Multiple, 3: RecurrentNDay, 4: RecurrentWeekly)> ");
        Integer response = sc.nextInt();
        sc.nextLine();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH 'Hours' mm 'Minute'");
        SimpleDateFormat departureFormat = new SimpleDateFormat("hh:mm a");
        
        if(response == 4)
        {
            newFSP.setScheduleType(ScheduleTypeEnum.RECURRENTWEEKLY);
            System.out.println("Enter Day Of Week> ");
            String dayOfWeek = sc.nextLine();
            System.out.println("Enter Departure Time> eg: 9:00 AM");
            String departureTimestr = sc.nextLine();
            System.out.println("Enter Start Date> dd MMM yy");
            String startDateStr = sc.nextLine();
            System.out.println("Enter End Date> dd MMM yy");
            String endDateStr = sc.nextLine();
            System.out.println("Enter Flight Duration> HH Hours mm Minutes");
            String flightDurationStr = sc.nextLine();
            
            //set the departureTime and flightDuration of the new FS
           
            //call the FS sessionBean to create the FS
            
      
            // Parse dates and FlightDuration
            try
            {
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);
                Date flightDuration = timeFormat.parse(flightDurationStr);
                Date departureTime = departureFormat.parse(departureTimestr);

                
                newFSP.setDayOfWeek(dayOfWeek);
                newFSP.setStartDate(startDate);
                newFSP.setEndDate(endDate);
                newFSP.setNdays(7);
                newFS.setDepartureTime(departureTime);
                newFS.setEstimatedFlightDuration(flightDuration);
               
                
                Long newfspid = flightSchedulePlanSessionBean.createNewRWFlightSchedulePlan(f, newFSP, newFS);
                if(newfspid == null)
                {
                    System.out.println("newfspid is null");
                } else 
                {
                    Long newfsid = flightScheduleSessionBean.createNewFlightSchedule(newFS, newfspid);
                }
                    
               
                //em.persist(newFSP)
                
            } catch (ParseException ex)
            {
                ex.printStackTrace();
            }
        } else if(response == 3)
        {
            newFSP.setScheduleType(ScheduleTypeEnum.RECURRENTNDAY);
            System.out.println("Enter NDay> ");
            Integer nDay = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Departure Time> eg: 9:00 AM");
            String departureTimestr = sc.nextLine();
            System.out.println("Enter Start Date> dd MMM yy");
            String startDateStr = sc.nextLine();
            System.out.println("Enter End Date> dd MMM yy");
            String endDateStr = sc.nextLine();
            System.out.println("Enter Flight Duration> HH Hours mm Minutes");
            String flightDurationStr = sc.nextLine();


            // Parse dates and FlightDuration
            try
            {
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);
                Date flightDuration = timeFormat.parse(flightDurationStr);
                Date departureTime = departureFormat.parse(departureTimestr);

                newFSP.setStartDate(startDate);
                newFSP.setEndDate(endDate);
                

                               
            } catch (ParseException ex)
            {
                ex.printStackTrace();
            }
            
        } else if(response == 2)
        {
            newFSP.setScheduleType(ScheduleTypeEnum.MULTIPLE);
            
        }else if(response == 1)
        {
            newFSP.setScheduleType(ScheduleTypeEnum.SINGLE);
            
        }
    } 
    
    private void doViewAllFlightSchedulePlan()
    {
        Flight f = new Flight();
        Scanner sc = new Scanner(System.in);
        System.out.println("*** FRS Schedule Manager Menu: View All Flight Schedule Plan ***\n");
        System.out.println("Enter Flight Number> ");
        String flightnumber = sc.nextLine();
        try
        {      
            f = flightSessionBean.retrieveFlightByFlightNumber(flightnumber);
            f.getFlightscheduleplans().size();
        } catch (FlightNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        for(int i = 0; i < f.getFlightscheduleplans().size(); i++) 
        {
            System.out.println("FSP " + f.getFlightscheduleplans().get(i).getFlightscheduleplanid() + ": with dayOfweek: " + f.getFlightscheduleplans().get(i).getDayOfWeek());
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
        System.out.print("Do you want to create a complementary return route? (Y: Yes, N: No) > ");
        String returnRoute = scanner.nextLine().trim();
        
        FlightRoute flightRoute = new FlightRoute(origin, destination);
        
        if (returnRoute.equalsIgnoreCase("Y")) {
            flightRoute.setReturnFlight(Boolean.TRUE);
        } else if (returnRoute.equalsIgnoreCase("N")) {
            flightRoute.setReturnFlight(Boolean.FALSE);
        }
        
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
    
    private void showInputDataValidationErrorsForFlight(Set<ConstraintViolation<Flight>>constraintViolations)
    {
        System.out.println("\nInput data validation error!:");
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            System.out.println("\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage());
        }

        System.out.println("\nPlease try again......\n");
    }
}
