
package rw.bk.taxi24app.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TAXI_RIDER")
public class TaxiRider implements Serializable {

    @Id
    @Column(name = "ID")
    Long id;
    @Column(name = "FIRST_NAME")
    String firstName;
    @Column(name = "LAST_NAME")
    String lastName;
    @Column(name = "PHONE_NUMBER")
    String phoneNumber;
    @Column(name = "RIDER_ADDRESS")
    String riderAddress;
    @Column(name = "EMAIL")
    String email;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_REGISTERED")
    Date dateRegistered = new Date();
    @Column(name = "STATUS")
    private String status;
     @Column(name = "LAST_KNOWN_COORDS")
    String lastKnownCoords;
     
     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRiderAddress() {
        return riderAddress;
    }

    public void setRiderAddress(String riderAddress) {
        this.riderAddress = riderAddress;
    }



    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastKnownCoords() {
        return lastKnownCoords;
    }

    public void setLastKnownCoords(String lastKnownCoords) {
        this.lastKnownCoords = lastKnownCoords;
    }

    
}
