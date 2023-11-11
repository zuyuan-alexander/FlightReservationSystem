/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import util.enumeration.ScheduleTypeEnum;

/**
 *
 * @author alvintjw
 */
@Entity
public class FlightSchedulePlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightscheduleplanid;
    @Column(nullable = false, length = 32)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ScheduleTypeEnum scheduleType;
    @Column(nullable = false)
    @NotNull
    private Date endDate;        
    @Column(nullable = true)
    @NotNull
    private Boolean disabled;

    public Long getFlightscheduleplanid() {
        return flightscheduleplanid;
    }

    public void setFlightscheduleplanid(Long flightscheduleplanid) {
        this.flightscheduleplanid = flightscheduleplanid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightscheduleplanid != null ? flightscheduleplanid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightscheduleplanid fields are not set
        if (!(object instanceof FlightSchedulePlan)) {
            return false;
        }
        FlightSchedulePlan other = (FlightSchedulePlan) object;
        if ((this.flightscheduleplanid == null && other.flightscheduleplanid != null) || (this.flightscheduleplanid != null && !this.flightscheduleplanid.equals(other.flightscheduleplanid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightSchedulePlan[ id=" + flightscheduleplanid + " ]";
    }
    
}
