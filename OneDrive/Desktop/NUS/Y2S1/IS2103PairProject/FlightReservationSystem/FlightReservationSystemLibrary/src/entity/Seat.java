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
import util.enumeration.SeatStatusEnum;

/**
 *
 * @author zuyua
 */
@Entity
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;
    @Column(nullable=false)
    private Integer rowNumber;
    @Column(nullable=false)
    private Character seatLetter;
    @Column(nullable=false)
    private SeatStatusEnum seatStatus;
    
    // relationship

    public Seat() {
    }

    public Seat(Integer rowNumber, Character seatLetter, SeatStatusEnum seatStatus) {
        this();
        this.rowNumber = rowNumber;
        this.seatLetter = seatLetter;
        this.seatStatus = seatStatus;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getSeatId() != null ? this.getSeatId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seat)) {
            return false;
        }
        Seat other = (Seat) object;
        if ((this.getSeatId()== null && other.getSeatId() != null) || (this.getSeatId() != null && !this.seatId.equals(other.seatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Seat[ seatId=" + this.getSeatId() + " ]";
    }

    /**
     * @return the seatId
     */
    public Long getSeatId() {
        return seatId;
    }

    /**
     * @param seatId the seatId to set
     */
    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    /**
     * @return the rowNumber
     */
    public Integer getRowNumber() {
        return rowNumber;
    }

    /**
     * @param rowNumber the rowNumber to set
     */
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * @return the seatLetter
     */
    public Character getSeatLetter() {
        return seatLetter;
    }

    /**
     * @param seatLetter the seatLetter to set
     */
    public void setSeatLetter(Character seatLetter) {
        this.seatLetter = seatLetter;
    }

    /**
     * @return the seatStatus
     */
    public SeatStatusEnum getSeatStatus() {
        return seatStatus;
    }

    /**
     * @param seatStatus the seatStatus to set
     */
    public void setSeatStatus(SeatStatusEnum seatStatus) {
        this.seatStatus = seatStatus;
    }
    
}
