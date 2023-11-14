/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
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
import util.exception.FlightReservationNotFoundException;
*/
/**
 *
 * @author zuyua
 */

/*
public class MainApp {

    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private FlightReservationSessionBeanRemote flightReservationSessionBeanRemote;
    private Customer customer;
    
    public MainApp() {
    }

    public MainApp(CustomerSessionBeanRemote customerSessionBeanRemote, FlightReservationSessionBeanRemote flightReservationSessionBeanRemote) {
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.flightReservationSessionBeanRemote = flightReservationSessionBeanRemote;
    }
    
    public void runApp() {
        
    }
    
    public void doSearhFlight() {
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
        
        // implementation
        List<FlightReservation> flightReservationList;
        if (tripTypeInt == 1) {
            flightReservationList = flightReservationSessionBeanRemote.searchFlight(tripType, departureAirport, destinationAirport, departureDate, numOfPassengers, flightPreference, cabinClassPreference);
        } else if (tripTypeInt == 2) {
            flightReservationList = flightReservationSessionBeanRemote.searchFlightWithReturnDate(tripType, departureAirport, destinationAirport, departureDate, returnDate, numOfPassengers, flightPreference, cabinClassPreference);
        }
        
        // display the list of reservations
        
    }
    
    public void doViewMyFlightReservations() {
        System.out.println("===== View My Flight Reservations =====");
        List<FlightReservation> flightReservations = flightReservationSessionBeanRemote.viewMyFlightReservations(customer);
        System.out.println("For Customer (" + customer.getUsername() + "), ");
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
            System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Flight Schedule: ");
            System.out.println("Seat Number: " + "\n");
        } catch (FlightReservationNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
}
*/