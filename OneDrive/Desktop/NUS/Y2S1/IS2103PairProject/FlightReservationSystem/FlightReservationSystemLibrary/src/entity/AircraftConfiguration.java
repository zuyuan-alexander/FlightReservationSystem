/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author zuyua
 */
@Entity
public class AircraftConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftConfigurationId;
    @Column(nullable=false, length=64, unique=true)
    //@NotNull
    //@Size(max=64)
    private String aircraftConfigurationName;
    @Column(nullable=false)
    //@NotNull
    //@Positive
    //@Min(1)
    //@Max(4)
    private Integer numOfCabinClass;
    @Column(nullable=false)
    private Integer maximumCapacity;

    // relationship
    @ManyToOne(optional=false)
    @JoinColumn(nullable=false)
    private AircraftType aircraftType;
    
    @OneToMany(mappedBy="aircraftConfiguration")
    private List<CabinClass> cabinClasses;

    public AircraftConfiguration() {
    }

    public AircraftConfiguration(String aircraftConfigurationName, Integer numOfCabinClass, Integer maximumCapacity) {
        this();
        this.aircraftConfigurationName = aircraftConfigurationName;
        this.numOfCabinClass = numOfCabinClass;
        this.maximumCapacity = maximumCapacity;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getAircraftConfigurationId() != null ? this.getAircraftConfigurationId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AircraftConfiguration)) {
            return false;
        }
        AircraftConfiguration other = (AircraftConfiguration) object;
        if ((this.getAircraftConfigurationId() == null && other.getAircraftConfigurationId() != null) || (this.getAircraftConfigurationId() != null && !this.aircraftConfigurationId.equals(other.aircraftConfigurationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.aircraftConfigurationName;
    }

    /**
     * @return the aircraftConfigurationId
     */
    public Long getAircraftConfigurationId() {
        return aircraftConfigurationId;
    }

    /**
     * @param aircraftConfigurationId the aircraftConfigurationId to set
     */
    public void setAircraftConfigurationId(Long aircraftConfigurationId) {
        this.aircraftConfigurationId = aircraftConfigurationId;
    }

    /**
     * @return the aircraftConfigurationName
     */
    public String getAircraftConfigurationName() {
        return aircraftConfigurationName;
    }

    /**
     * @param aircraftConfigurationName the aircraftConfigurationName to set
     */
    public void setAircraftConfigurationName(String aircraftConfigurationName) {
        this.aircraftConfigurationName = aircraftConfigurationName;
    }

    /**
     * @return the numOfCabinClass
     */
    public Integer getNumOfCabinClass() {
        return numOfCabinClass;
    }

    /**
     * @param numOfCabinClass the numOfCabinClass to set
     */
    public void setNumOfCabinClass(Integer numOfCabinClass) {
        this.numOfCabinClass = numOfCabinClass;
    }

    /**
     * @return the aircraftType
     */
    public AircraftType getAircraftType() {
        return aircraftType;
    }

    /**
     * @param aircraftType the aircraftType to set
     */
    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    /**
     * @return the cabinClasses
     */
    public List<CabinClass> getCabinClasses() {
        return cabinClasses;
    }

    /**
     * @param cabinClasses the cabinClasses to set
     */
    public void setCabinClasses(List<CabinClass> cabinClasses) {
        this.cabinClasses = cabinClasses;
    }

    /**
     * @return the maximumCapacity
     */
    public Integer getMaximumCapacity() {
        return maximumCapacity;
    }

    /**
     * @param maximumCapacity the maximumCapacity to set
     */
    public void setMaximumCapacity(Integer maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }
    
}
