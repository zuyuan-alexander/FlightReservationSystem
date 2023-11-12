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
public class Aircraft implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftId;
    @Column(nullable=false, length=64, unique=true)
    //@NotNull
    //@Size(max=64)
    private String model;
    @Column(nullable=false)
    //@NotNull
    private AircraftTypeEnum aircraftTypeName;
    @Column(nullable=false)
    //@NotNull
    //@Positive
    //@Min(1)
    private Integer maxPassengerSeatCapacity;

    // relationship

    public Aircraft() {
    }

    public Aircraft(String model, AircraftTypeEnum aircraftTypeName, Integer maxPassengerSeatCapacity) {
        this();
        this.model = model;
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
        if (!(object instanceof Aircraft)) {
            return false;
        }
        Aircraft other = (Aircraft) object;
        if ((this.getAircraftId() == null && other.getAircraftId() != null) || (this.getAircraftId() != null && !this.aircraftId.equals(other.aircraftId))) {
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
        return aircraftId;
    }

    /**
     * @param aircraftId the aircraftId to set
     */
    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the aircraftTypeName
     */
    public AircraftTypeEnum getAircraftTypeName() {
        return aircraftTypeName;
    }

    /**
     * @param aircraftTypeName the aircraftTypeName to set
     */
    public void setAircraftTypeName(AircraftTypeEnum aircraftTypeName) {
        this.aircraftTypeName = aircraftTypeName;
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
