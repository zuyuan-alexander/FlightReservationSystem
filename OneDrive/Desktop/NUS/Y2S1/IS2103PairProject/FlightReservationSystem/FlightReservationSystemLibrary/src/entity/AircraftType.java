/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import util.enumeration.AircraftTypeEnum;

/**
 *
 * @author zuyua
 */
@Entity
public class AircraftType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftTypeId;
    @Column(nullable=false)
    //@NotNull
    private String aircraftTypeName;
    @Column(nullable=false)
    //@NotNull
    //@Positive
    //@Min(1)
    private Integer maxPassengerSeatCapacity;

    // relationship

    public AircraftType() {
    }

    public AircraftType(String aircraftTypeName, Integer maxPassengerSeatCapacity) {
        this();
        this.aircraftTypeName = aircraftTypeName;
        this.maxPassengerSeatCapacity = maxPassengerSeatCapacity;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getAircraftId() != null ? this.getAircraftId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AircraftType)) {
            return false;
        }
        AircraftType other = (AircraftType) object;
        if ((this.getAircraftId() == null && other.getAircraftId() != null) || (this.getAircraftId() != null && !this.aircraftTypeId.equals(other.aircraftTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Aircraft[ aircraftId=" + this.getAircraftId() + " ]";
    }

    /**
     * @return the aircraftId
     */
    public Long getAircraftId() {
        return aircraftTypeId;
    }

    /**
     * @param aircraftId the aircraftId to set
     */
    public void setAircraftId(Long aircraftTypeId) {
        this.aircraftTypeId = aircraftTypeId;
    }

    /**
     * @return the maxPassengerSeatCapacity
     */
    public Integer getMaxPassengerSeatCapacity() {
        return maxPassengerSeatCapacity;
    }

    /**
     * @param maxPassengerSeatCapacity the maxPassengerSeatCapacity to set
     */
    public void setMaxPassengerSeatCapacity(Integer maxPassengerSeatCapacity) {
        this.maxPassengerSeatCapacity = maxPassengerSeatCapacity;
    }
    
}
