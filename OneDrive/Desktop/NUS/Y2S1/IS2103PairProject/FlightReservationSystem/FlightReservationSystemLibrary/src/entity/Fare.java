/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.enumeration.CabinClassTypeEnum;

/**
 *
 * @author alvintjw
 */
@Entity
public class Fare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fareid;
    @Column(nullable = false, length = 8)
    @NotNull
    @Size(min = 3, max = 7)
    private String fareBasicCode;
    private BigDecimal fareAmount;
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private CabinClassTypeEnum cabinClassType;

    // relationship
    @ManyToOne(optional=false)
    @JoinColumn(nullable=false)
    private FlightSchedulePlan flightSchedulePlan;

    public Fare() {
    }

    public Fare(String fareBasicCode, BigDecimal fareAmount, CabinClassTypeEnum cabinClassType) {
        this();
        this.fareBasicCode = fareBasicCode;
        this.fareAmount = fareAmount;
        this.cabinClassType = cabinClassType;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getFareid() != null ? getFareid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the fareid fields are not set
        if (!(object instanceof Fare)) {
            return false;
        }
        Fare other = (Fare) object;
        if ((this.getFareid() == null && other.getFareid() != null) || (this.getFareid() != null && !this.fareid.equals(other.fareid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Fare[ id=" + getFareid() + " ]";
    }

    /**
     * @return the fareid
     */
    public Long getFareid() {
        return fareid;
    }

    /**
     * @param fareid the fareid to set
     */
    public void setFareid(Long fareid) {
        this.fareid = fareid;
    }

    /**
     * @return the fareBasicCode
     */
    public String getFareBasicCode() {
        return fareBasicCode;
    }

    /**
     * @param fareBasicCode the fareBasicCode to set
     */
    public void setFareBasicCode(String fareBasicCode) {
        this.fareBasicCode = fareBasicCode;
    }

    /**
     * @return the fareAmount
     */
    public BigDecimal getFareAmount() {
        return fareAmount;
    }

    /**
     * @param fareAmount the fareAmount to set
     */
    public void setFareAmount(BigDecimal fareAmount) {
        this.fareAmount = fareAmount;
    }

    /**
     * @return the cabinClassType
     */
    public CabinClassTypeEnum getCabinClassType() {
        return cabinClassType;
    }

    /**
     * @param cabinClassType the cabinClassType to set
     */
    public void setCabinClassType(CabinClassTypeEnum cabinClassType) {
        this.cabinClassType = cabinClassType;
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
    
}
