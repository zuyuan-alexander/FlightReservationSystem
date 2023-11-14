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
import util.enumeration.TripTypeEnum;

/**
 *
 * @author alvintjw
 */
@Entity
public class FlightReservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightreservationid;
    @Column(nullable=false)
    private TripTypeEnum tripTypeEnum;
    
    // relationship

    public FlightReservation() {
    }

    public FlightReservation(TripTypeEnum tripTypeEnum) {
        this();
        this.tripTypeEnum = tripTypeEnum;
    }
    
    

    public Long getFlightreservationid() {
        return flightreservationid;
    }

    public void setFlightreservationid(Long flightreservationid) {
        this.flightreservationid = flightreservationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightreservationid != null ? flightreservationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightreservationid fields are not set
        if (!(object instanceof FlightReservation)) {
            return false;
        }
        FlightReservation other = (FlightReservation) object;
        if ((this.flightreservationid == null && other.flightreservationid != null) || (this.flightreservationid != null && !this.flightreservationid.equals(other.flightreservationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightReservation[ id=" + flightreservationid + " ]";
    }

    /**
     * @return the tripTypeEnum
     */
    public TripTypeEnum getTripTypeEnum() {
        return tripTypeEnum;
    }

    /**
     * @param tripTypeEnum the tripTypeEnum to set
     */
    public void setTripTypeEnum(TripTypeEnum tripTypeEnum) {
        this.tripTypeEnum = tripTypeEnum;
    }
    
}
