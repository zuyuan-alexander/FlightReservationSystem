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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import util.enumeration.CabinClassTypeEnum;

/**
 *
 * @author zuyua
 */
@Entity
public class CabinClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cabinClassId;
    @Column(nullable=false)
    //@NotNull
    private CabinClassTypeEnum cabinClassType;
    @Column(nullable=false)
    //@NotNull
    //@Positive
    //@Min(1)
    private Integer numOfAisles;
    @Column(nullable=false)
    //@Positive
    //@Min(1)
    private Integer numOfRows;
    @Column(nullable=false)
    //@Positive
    //@Min(1)
    private Integer numOfSeatsAbreast;
    @Column(nullable=false, length=5)
    //@Size(max=5)
    private String actualSeatConfiguration;

    // relationship

    public CabinClass() {
    }

    public CabinClass(CabinClassTypeEnum cabinClassType, Integer numOfAisles, Integer numOfRows, Integer numOfSeatsAbreast, String actualSeatConfiguration) {
        this();
        this.cabinClassType = cabinClassType;
        this.numOfAisles = numOfAisles;
        this.numOfRows = numOfRows;
        this.numOfSeatsAbreast = numOfSeatsAbreast;
        this.actualSeatConfiguration = actualSeatConfiguration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getCabinClassId() != null ? this.getCabinClassId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabinClass)) {
            return false;
        }
        CabinClass other = (CabinClass) object;
        if ((this.getCabinClassId() == null && other.getCabinClassId() != null) || (this.getCabinClassId() != null && !this.cabinClassId.equals(other.cabinClassId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CabinClass[ cabinClassId=" + this.getCabinClassId() + " ]";
    }

    /**
     * @return the cabinClassId
     */
    public Long getCabinClassId() {
        return cabinClassId;
    }

    /**
     * @param cabinClassId the cabinClassId to set
     */
    public void setCabinClassId(Long cabinClassId) {
        this.cabinClassId = cabinClassId;
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
     * @return the numOfAisles
     */
    public Integer getNumOfAisles() {
        return numOfAisles;
    }

    /**
     * @param numOfAisles the numOfAisles to set
     */
    public void setNumOfAisles(Integer numOfAisles) {
        this.numOfAisles = numOfAisles;
    }

    /**
     * @return the numOfRows
     */
    public Integer getNumOfRows() {
        return numOfRows;
    }

    /**
     * @param numOfRows the numOfRows to set
     */
    public void setNumOfRows(Integer numOfRows) {
        this.numOfRows = numOfRows;
    }

    /**
     * @return the numOfSeatsAbreast
     */
    public Integer getNumOfSeatsAbreast() {
        return numOfSeatsAbreast;
    }

    /**
     * @param numOfSeatsAbreast the numOfSeatsAbreast to set
     */
    public void setNumOfSeatsAbreast(Integer numOfSeatsAbreast) {
        this.numOfSeatsAbreast = numOfSeatsAbreast;
    }

    /**
     * @return the actualSeatConfiguration
     */
    public String getActualSeatConfiguration() {
        return actualSeatConfiguration;
    }

    /**
     * @param actualSeatConfiguration the actualSeatConfiguration to set
     */
    public void setActualSeatConfiguration(String actualSeatConfiguration) {
        this.actualSeatConfiguration = actualSeatConfiguration;
    }
    
}
