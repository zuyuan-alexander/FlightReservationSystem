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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author zuyua
 */
@Entity
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;
    @Column(nullable=false, length=64, unique=true)
    @NotNull
    @Size(max=64)
    private String airportName;
    @Column(nullable=false, length=64, unique=true)
    @NotNull
    @Size(max=64)
    private String iataAirportCode;
    @Column(nullable=false, length=64)
    @NotNull
    @Size(max=64)
    private String city;
    @Column(nullable=false, length=64)
    @NotNull
    @Size(max=64)
    private String stateprovince;
    @Column(nullable=false, length=64)
    @NotNull
    @Size(max=64)
    private String country;

    // relationship

    public Airport() {
    }

    public Airport(String airportName, String iataAirportCode, String city, String stateprovince, String country) {
        this();
        this.airportName = airportName;
        this.iataAirportCode = iataAirportCode;
        this.city = city;
        this.stateprovince = stateprovince;
        this.country = country;
    } 
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getAirportId() != null ? this.getAirportId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airport)) {
            return false;
        }
        Airport other = (Airport) object;
        if ((this.getAirportId() == null && other.getAirportId() != null) || (this.getAirportId() != null && !this.airportId.equals(other.airportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Airport[ airportId=" + this.getAirportId() + " ]";
    }

    /**
     * @return the airportId
     */
    public Long getAirportId() {
        return airportId;
    }

    /**
     * @param airportId the airportId to set
     */
    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    /**
     * @return the airportName
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * @param airportName the airportName to set
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    /**
     * @return the iataAirportCode
     */
    public String getIataAirportCode() {
        return iataAirportCode;
    }

    /**
     * @param iataAirportCode the iataAirportCode to set
     */
    public void setIataAirportCode(String iataAirportCode) {
        this.iataAirportCode = iataAirportCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the stateprovince
     */
    public String getStateprovince() {
        return stateprovince;
    }

    /**
     * @param stateprovince the stateprovince to set
     */
    public void setStateprovince(String stateprovince) {
        this.stateprovince = stateprovince;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
}
