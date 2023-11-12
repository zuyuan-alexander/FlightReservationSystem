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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public Long getFareid() {
        return fareid;
    }

    public void setFareid(Long fareid) {
        this.fareid = fareid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fareid != null ? fareid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the fareid fields are not set
        if (!(object instanceof Fare)) {
            return false;
        }
        Fare other = (Fare) object;
        if ((this.fareid == null && other.fareid != null) || (this.fareid != null && !this.fareid.equals(other.fareid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Fare[ id=" + fareid + " ]";
    }
    
}
