/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alvintjw
 */
@Entity
public class FlightSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightscheduleid;
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date departureDate;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @NotNull
    private Date departureTime;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @NotNull
    private Date estimatedFlightDuration;
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @NotNull
    private Date arrivalTime;
   

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private FlightSchedulePlan flightSchedulePlan;
    
    /*
    @OneToMany(mappedBy="flightSchedule")
    private List<Seat> seats;
    */
    
    @OneToMany(mappedBy="flightSchedule")
    private List<Passenger> passengers;
    
    @ManyToOne(optional=true)
    @JoinColumn(nullable=true)
    private FlightReservation flightReservation;

    public FlightSchedule() {
        //this.seats = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public FlightSchedule(Date departureDate, Date departureTime, Date estimatedFlightDuration) {
        this();
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.estimatedFlightDuration = estimatedFlightDuration;
        calculateAndSetArrivalDateTime();
     }
    
    

    
    public Long getFlightscheduleid() {
        return flightscheduleid;
    }

    public void setFlightscheduleid(Long flightscheduleid) {
        this.flightscheduleid = flightscheduleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightscheduleid != null ? flightscheduleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightscheduleid fields are not set
        if (!(object instanceof FlightSchedule)) {
            return false;
        }
        FlightSchedule other = (FlightSchedule) object;
        if ((this.flightscheduleid == null && other.flightscheduleid != null) || (this.flightscheduleid != null && !this.flightscheduleid.equals(other.flightscheduleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightSchedule[ id=" + flightscheduleid + " ]";
    }

  
    /**
     * @return the departureDate
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * @return the departureTime
     */
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @return the estimatedFlightDuration
     */
    public Date getEstimatedFlightDuration() {
        return estimatedFlightDuration;
    }

    /**
     * @param estimatedFlightDuration the estimatedFlightDuration to set
     */
    public void setEstimatedFlightDuration(Date estimatedFlightDuration) {
        this.estimatedFlightDuration = estimatedFlightDuration;
    }

    /**
     * @return the arrivalDate
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @param arrivalDate the arrivalDate to set
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * @return the flightSchedulePlan
     */
    public FlightSchedulePlan getFlightSchedulePlan() {
        return flightSchedulePlan;
    }

    /**
     * @param flightSchedulePlan the flightSchedulePlan to set
     */
    public void setFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan) {
        this.flightSchedulePlan = flightSchedulePlan;
    }

    /*
    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    */
    
  public void calculateAndSetArrivalDateTime() {
    if (departureDate != null && departureTime != null && estimatedFlightDuration != null) {
        Calendar departureDateTime = Calendar.getInstance();
        departureDateTime.setTime(departureDate);

        Calendar departureTimeCalendar = Calendar.getInstance();
        departureTimeCalendar.setTime(departureTime);
        departureDateTime.set(Calendar.HOUR_OF_DAY, departureTimeCalendar.get(Calendar.HOUR_OF_DAY));
        departureDateTime.set(Calendar.MINUTE, departureTimeCalendar.get(Calendar.MINUTE));
        departureDateTime.set(Calendar.SECOND, departureTimeCalendar.get(Calendar.SECOND));

        long estimatedFlightDurationInMillis = estimatedFlightDuration.getTime();

        long arrivalDateTimeInMillis = departureDateTime.getTimeInMillis() + estimatedFlightDurationInMillis;
        Date arrivalDateTime = new Date(arrivalDateTimeInMillis);

        // Break down arrivalDateTime into arrivalDate and arrivalTime
        Calendar arrivalCal = Calendar.getInstance();
        arrivalCal.setTime(arrivalDateTime);
        
        this.arrivalDate = new Date(arrivalCal.getTimeInMillis() - (arrivalCal.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000)
                                - (arrivalCal.get(Calendar.MINUTE) * 60 * 1000)
                                - (arrivalCal.get(Calendar.SECOND) * 1000)
                                - (arrivalCal.get(Calendar.MILLISECOND)));
        
        this.arrivalTime = new Time(arrivalDateTime.getTime() - this.arrivalDate.getTime());
    }
}

    /**
     * @return the passengers
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * @param passengers the passengers to set
     */
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    /**
     * @return the flightReservation
     */
    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    /**
     * @param flightReservation the flightReservation to set
     */
    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }
    
    
    
}
    
