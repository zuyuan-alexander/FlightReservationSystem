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

/**
 *
 * @author zuyua
 */
@Entity
public class FlightRoute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightRouteId;
    @Column(nullable=false, length=64)
    private String origin;
    @Column(nullable=false, length=64)
    private String destination;
    @Column(nullable=false)
    private Boolean returnFlight;
    @Column(nullable=false)
    private Boolean disabledFlight;

    // relationship

    public FlightRoute() {
    }

    public FlightRoute(String origin, String destination, Boolean returnFlight, Boolean disabledFlight) {
        this();
        this.origin = origin;
        this.destination = destination;
        this.returnFlight = returnFlight;
        this.disabledFlight = disabledFlight;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getFlightRouteId() != null ? this.getFlightRouteId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlightRoute)) {
            return false;
        }
        FlightRoute other = (FlightRoute) object;
        if ((this.getFlightRouteId() == null && other.getFlightRouteId() != null) || (this.getFlightRouteId() != null && !this.flightRouteId.equals(other.flightRouteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightRoute[ flightRouteId=" + this.getFlightRouteId() + " ]";
    }

    /**
     * @return the flightRouteId
     */
    public Long getFlightRouteId() {
        return flightRouteId;
    }

    /**
     * @param flightRouteId the flightRouteId to set
     */
    public void setFlightRouteId(Long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the returnFlight
     */
    public Boolean getReturnFlight() {
        return returnFlight;
    }

    /**
     * @param returnFlight the returnFlight to set
     */
    public void setReturnFlight(Boolean returnFlight) {
        this.returnFlight = returnFlight;
    }

    /**
     * @return the disabledFlight
     */
    public Boolean getDisabledFlight() {
        return disabledFlight;
    }

    /**
     * @param disabledFlight the disabledFlight to set
     */
    public void setDisabledFlight(Boolean disabledFlight) {
        this.disabledFlight = disabledFlight;
    }
    
}
