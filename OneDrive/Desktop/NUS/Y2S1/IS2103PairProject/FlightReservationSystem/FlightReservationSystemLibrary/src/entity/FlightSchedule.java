/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    @Column(nullable = false)
    @NotNull
    private Date endDate;   
    @Column(nullable = false)
    @NotNull
    private Date departureDate;
    @Column(nullable = false)
    @NotNull
    private Time departureTime;
    @Column(nullable = false)
    @NotNull
    private Time estimatedFlightDuration;
    @Column(nullable = false)
    @NotNull
    private Date arrivalDate;
    @Column(nullable = false)
    @NotNull
    private Time arrivalTime;
    
    //relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private FlightSchedulePlan FlightSchedulePlan;

    @ManyToMany(mappedBy = "FlightSchedule")
    private List<Passenger> passengers = new ArrayList<Passenger>();

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
    
}
