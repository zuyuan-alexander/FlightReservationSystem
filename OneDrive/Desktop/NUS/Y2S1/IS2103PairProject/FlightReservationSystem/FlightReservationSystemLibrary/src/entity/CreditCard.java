/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alvintjw
 */
@Entity
public class CreditCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditcardid;
    @Column(nullable = false, length = 16, unique = true)
    @NotNull
    @Size(min = 16, max = 16)
    private String creditCardNumber;
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(min = 1, max = 32)
    private String creditCardName;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Column(nullable = false, length = 4)
    @NotNull
    @Size(min = 3, max = 4)
    private String cvv;


    public Long getCreditcardid() {
        return creditcardid;
    }

    public void setCreditcardid(Long creditcardid) {
        this.creditcardid = creditcardid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditcardid != null ? creditcardid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the creditcardid fields are not set
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard other = (CreditCard) object;
        if ((this.creditcardid == null && other.creditcardid != null) || (this.creditcardid != null && !this.creditcardid.equals(other.creditcardid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditCard[ id=" + creditcardid + " ]";
    }
    
}
