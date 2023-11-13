/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.enumeration.EmployeeTypeEnum;

/**
 *
 * @author alvintjw
 */
@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeid;
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(min = 1, max = 32)
    private String name;
    @Column(nullable = false, length = 32, unique = true)
    @NotNull
    @Size(min = 1, max = 32)
    private String username;
    @Column(nullable = false, length = 64) // Adjust the length as needed
    @NotNull
    @Size(min = 1, max = 64)
    private String password;
    @Enumerated(EnumType.STRING)
    private EmployeeTypeEnum userRole;

    public Employee(String name, String username, String password, EmployeeTypeEnum userRole) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public Employee() {
    }

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeid != null ? employeeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the employeeid fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeid == null && other.employeeid != null) || (this.employeeid != null && !this.employeeid.equals(other.employeeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Employee[ id=" + employeeid + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
   

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userRole
     */
    public EmployeeTypeEnum getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(EmployeeTypeEnum userRole) {
        this.userRole = userRole;
    }
    
}
