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

/**
 *
 * @author zuyua
 */
@Entity
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    @Column(nullable=false, unique=true)
    private Integer flightNumber;

    @ManyToOne(optional=true)
    @JoinColumn(nullable=true)
    private FlightRoute flightRoute;
    
    @ManyToOne(optional=false)
    @JoinColumn(nullable=false)
    private Aircraft aircraft;

    public Flight() {
    }

    public Flight(Integer flightNumber) {
        this();
        this.flightNumber = flightNumber;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getFlightId() != null ? this.getFlightId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.getFlightId() == null && other.getFlightId() != null) || (this.getFlightId() != null && !this.flightId.equals(other.flightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Flight[ flightId=" + this.getFlightId() + " ]";
    }

    /**
     * @return the flightId
     */
    public Long getFlightId() {
        return flightId;
    }

    /**
     * @param flightId the flightId to set
     */
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    /**
     * @return the flightNumber
     */
    public Integer getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return the flightRoute
     */
    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    /**
     * @param flightRoute the flightRoute to set
     */
    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    /**
     * @return the aircraft
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * @param aircraft the aircraft to set
     */
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
    
}
