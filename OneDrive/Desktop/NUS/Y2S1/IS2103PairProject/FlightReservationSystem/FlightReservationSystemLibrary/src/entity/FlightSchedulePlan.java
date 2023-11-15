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
import javax.persistence.OneToMany;
//import javax.validation.constraints.NegativeOrZero.List;
import javax.validation.constraints.NotNull;
import util.enumeration.ScheduleTypeEnum;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    
    @Column(nullable = true)
    private Boolean disabled;
    @Column(nullable = true, length = 10)
    private String dayOfWeek;
    @Column(nullable = true)
    private int Ndays;
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date startDate;   
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date endDate;   
    
    @OneToMany(mappedBy = "flightSchedulePlan", cascade = CascadeType.REMOVE)
    private List<FlightSchedule> flightschedules;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Flight flight;

    public FlightSchedulePlan() {
    }

    public FlightSchedulePlan(ScheduleTypeEnum scheduleType) {
        this.scheduleType = scheduleType;
        this.disabled = false;
    }
    
    
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

    /**
     * @return the scheduleType
     */
    public ScheduleTypeEnum getScheduleType() {
        return scheduleType;
    }

    /**
     * @param scheduleType the scheduleType to set
     */
    public void setScheduleType(ScheduleTypeEnum scheduleType) {
        this.scheduleType = scheduleType;
    }

    /**
     * @return the disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the dayOfWeek
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * @param dayOfWeek the dayOfWeek to set
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * @return the Ndays
     */
    public int getNdays() {
        return Ndays;
    }

    /**
     * @param Ndays the Ndays to set
     */
    public void setNdays(int Ndays) {
        this.Ndays = Ndays;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the flightschedules
     */
    public List<FlightSchedule> getFlightschedules() {
        return flightschedules;
    }

    /**
     * @param flightschedules the flightschedules to set
     */
    public void setFlightschedules(List<FlightSchedule> flightschedules) {
        this.flightschedules = flightschedules;
    }

    /**
     * @return the flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @param flight the flight to set
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
}
