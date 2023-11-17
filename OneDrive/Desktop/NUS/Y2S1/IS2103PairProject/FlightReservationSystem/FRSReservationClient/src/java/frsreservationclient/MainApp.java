/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package frsreservationclient;

import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.FlightReservationSessionBeanRemote;
import entity.Customer;
import entity.FlightReservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.enumeration.TripTypeEnum;
import util.exception.CustomerCredentialExistException;
import util.exception.FlightReservationNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zuyua
 */


public class MainApp {

    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private FlightReservationSessionBeanRemote flightReservationSessionBeanRemote;
    private Customer currentCustomer;
    
    public MainApp() {
    }

    public MainApp(CustomerSessionBeanRemote customerSessionBeanRemote, FlightReservationSessionBeanRemote flightReservationSessionBeanRemote) {
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.flightReservationSessionBeanRemote = flightReservationSessionBeanRemote;
    }
    
    public void runApp() {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to FRS Mangagement System***\n");
            System.out.println("1: Login");
            System.out.println("2: Register As Customer");
            System.out.println("3: Search Flight");
            System.out.println("4: Exit\n");
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
                        mainMenu();
                    }
                    catch(InvalidLoginCredentialException ex) 
                    {
                        System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                    }
                } else if (response == 2) {
                    doRegisterAsCustomer();
                } else if (response == 3) {
                    doSearchFlight();
                } else if (response == 4)
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
    
    private void doLogin() throws InvalidLoginCredentialException
    {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";
        
        System.out.println("*** FRS Reservation System :: Login ***\n");
        System.out.print("Enter username> ");
        username = scanner.nextLine().trim();
        System.out.print("Enter password> ");
        password = scanner.nextLine().trim();
        
        if(username.length() > 0 && password.length() > 0)
        {
            currentCustomer = customerSessionBeanRemote.customerLogin(username, password); 
        }
        else
        {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    public void doRegisterAsCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== Register As Customer =====");
        System.out.print("Enter first name > ");
        String firstName = sc.nextLine().trim();
        System.out.print("Enter last name > ");
        String lastName = sc.nextLine().trim();
        System.out.print("Enter email > ");
        String email = sc.nextLine().trim();
        System.out.print("Enter mobile phone number > ");
        String mobilePhoneNumber = sc.nextLine().trim();
        System.out.print("Enter address > ");
        String address = sc.nextLine().trim();
        System.out.print("Enter postalCode > ");
        String postalCode = sc.nextLine().trim();
        System.out.print("Enter username > ");
        String username = sc.nextLine().trim();
        System.out.print("Enter password > ");
        String password = sc.nextLine().trim();
        
        Customer newCustomer = new Customer(firstName, lastName, email, mobilePhoneNumber, address, postalCode, username, password);
        try {
            Long newCustomerId = customerSessionBeanRemote.registerAsCustomer(newCustomer);
            System.out.println("Customer with Customer Id " + newCustomerId + " has been successfully created!");
        } catch (UnknownPersistenceException ex) {
            System.out.println(ex.getMessage() + "\n");
        } catch (CustomerCredentialExistException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
    
    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while(true) {
            System.out.println("*** Welcome to Main Menu ***");
            System.out.println("1: Search Flight");
            System.out.println("2: Reserve Flight");
            System.out.println("3: View My Flight Reservation");
            System.out.println("4: View My Flight Reservation Details");
            System.out.println("5: Exit\n");
            
            while(response < 1 || response > 5) {
                System.out.print("> ");
                response = sc.nextInt();
                
                if (response == 1) {
                    doSearchFlight();
                } else if (response == 2) {
                    doReserveFlight();
                } else if (response == 3) {
                    doViewMyFlightReservations();
                } else if (response == 4) {
                    doViewMyFlightReservationDetails();
                } else if (response == 5) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            
            if (response == 5) {
                break;
            }
        }
    }
    
    public void doSearchFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Search Flight =====");
        System.out.print("Enter trip type (1: One-way, 2: Round-trip) > ");
        Integer tripTypeInt = scanner.nextInt();
        scanner.next();
        
        while (tripTypeInt < 1 || tripTypeInt > 2) {
            System.out.println("Invalid input. Please try again!");
            System.out.print("Enter trip type (1: One-way, 2: Round-trip) > ");
            tripTypeInt = scanner.nextInt();
            scanner.next();
        }
        
        TripTypeEnum tripType = null;
        if (tripTypeInt == 1) {
            tripType = TripTypeEnum.ONE_WAY;
        } else if (tripTypeInt == 2) {
            tripType = TripTypeEnum.ROUND_TRIP;
        }
        
        System.out.print("Enter departure airport > ");
        String departureAirport = scanner.nextLine().trim();
        System.out.print("Enter destination airport > ");
        String destinationAirport = scanner.nextLine().trim();
        System.out.print("Enter departure date (dd/mm/yyyy) > ");
        String dateString = scanner.nextLine().trim();
        
        Date departureDate = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            departureDate = dateFormat.parse(dateString);
        } catch (ParseException ex) {
            System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
        }
        
        Date returnDate = new Date();
        if (tripTypeInt == 2) {
            System.out.print("Enter return date (dd/mm/yyyy) > ");
            dateString = scanner.nextLine().trim();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                returnDate = dateFormat.parse(dateString);
            } catch (ParseException ex) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }
        
        System.out.print("Enter number of passengers > ");
        Integer numOfPassengers = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Do you have any flight preferences? (Y: Yes, N: No) > ");
        String flightPreferenceStr = scanner.nextLine().trim();
        Boolean flightPreference = Boolean.FALSE;
        if (flightPreferenceStr.equalsIgnoreCase("Y")) {
            flightPreference = Boolean.TRUE;
        } else if (flightPreferenceStr.equalsIgnoreCase("N")) {
            flightPreference = Boolean.FALSE;
        } else {
            System.out.println("Invalid Input!");
            return;
        }
        
        System.out.print("Do you have any cabin class preferences? (Y: Yes, N: No) > ");
        String cabinClassPreferenceStr = scanner.nextLine().trim();
        Boolean cabinClassPreference = Boolean.FALSE;
        if (cabinClassPreferenceStr.equalsIgnoreCase("Y")) {
            cabinClassPreference = Boolean.TRUE;
        } else if (cabinClassPreferenceStr.equalsIgnoreCase("N")) {
            cabinClassPreference = Boolean.FALSE;
        } else {
            System.out.println("Invalid Input!");
            return;
        }
        
        /*
        // implementation
        List<FlightReservation> flightReservationList = flightReservationSessionBeanRemote.searchFlight(tripType, departureAirport, destinationAirport, departureDate, numOfPassengers, flightPreference, cabinClassPreference);
        searchFlightOneWay();
        
        if (tripTypeInt == 2) {
            // we now change the departure date to return date
            flightReservationList = flightReservationSessionBeanRemote.searchFlight(tripType, departureAirport, destinationAirport, returnDate, numOfPassengers, flightPreference, cabinClassPreference);
            searchFlightRoundTrip();
        }
        */
        // display the list of reservations
        
    }
    
    public void searchFlightOneWay() {
        // on departure date
        
        // three days before departure date
        // three days after departure date
    }
    
    public void searchFlightRoundTrip() {}
    
    public void doReserveFlight() {
        System.out.print("Enter flight schedule id > ");
        System.out.print("Enter number of passengers > ");
        
    }
    
    public void doViewMyFlightReservations() {
        System.out.println("===== View My Flight Reservations =====");
        List<FlightReservation> flightReservations = flightReservationSessionBeanRemote.viewMyFlightReservations(currentCustomer);
        System.out.println("For Customer (" + currentCustomer.getUsername() + "), ");
        for (FlightReservation fr : flightReservations) {
            System.out.println("Flight Reservation Id: " + fr.getFlightreservationid() + "; Trip Type: " + fr.getTripTypeEnum());
        }
    }
    
    public void doViewMyFlightReservationDetails() {
        System.out.println("===== View My Flight Reservation Details =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter flight reservation id > ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            FlightReservation fr = flightReservationSessionBeanRemote.retrieveFlightReservationById(id);
            System.out.println("Flight Reservation Id: " + id);
            System.out.println("Customer: " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName());
            System.out.println("Flight Schedule: ");
            System.out.println("Seat Number: " + "\n");
        } catch (FlightReservationNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
}
