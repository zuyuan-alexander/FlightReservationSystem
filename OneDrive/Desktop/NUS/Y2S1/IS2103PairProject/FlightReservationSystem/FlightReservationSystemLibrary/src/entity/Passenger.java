/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alvintjw
 */
@Entity
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerid;
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(min = 1, max = 32)
    private String firstName;
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(min = 1, max = 32)
    private String lastName;
    @Column(nullable = false, length = 16)
    @NotNull
    @Size(min = 1, max = 16)
    private String passportNumber;
    
    @ManyToOne(optional=false)
    @JoinColumn(nullable=false)
    private FlightSchedule flightSchedule;
    
    @OneToOne(optional=false)
    private Seat seat;
    
    @OneToOne(optional=false)
    private FlightReservation flightreservation;

    public Passenger() {
    }

    public Passenger(String firstName, String lastName, String passportNumber) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPassengerid() != null ? getPassengerid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the passengerid fields are not set
        if (!(object instanceof Passenger)) {
            return false;
        }
        Passenger other = (Passenger) object;
        if ((this.getPassengerid() == null && other.getPassengerid() != null) || (this.getPassengerid() != null && !this.passengerid.equals(other.passengerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return the passengerid
     */
    public Long getPassengerid() {
        return passengerid;
    }

    /**
     * @param passengerid the passengerid to set
     */
    public void setPassengerid(Long passengerid) {
        this.passengerid = passengerid;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the passportNumber
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param passportNumber the passportNumber to set
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * @return the flightSchedule
     */
    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    /**
     * @param flightSchedule the flightSchedule to set
     */
    public void setFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    /**
     * @return the seat
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * @param seat the seat to set
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * @return the flightreservation
     */
    public FlightReservation getFlightreservation() {
        return flightreservation;
    }

    /**
     * @param flightreservation the flightreservation to set
     */
    public void setFlightreservation(FlightReservation flightreservation) {
        this.flightreservation = flightreservation;
    }
    
    
    
}