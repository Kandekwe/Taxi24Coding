
package rw.bk.taxi24app.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "TAXI_DRIVER")
public class TaxiDriver implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    String firstName;
    @Column(name = "LAST_NAME")
    String lastName;
    @Column(name = "GENDER")
    String gender;
    @Column(name = "STATUS")
    String status;
    @Column(name = "PHONE_NUMBER")
    String phoneNumber;
    @Column(name = "LICENCE_ID")
    String idNumber;
    @Column(name = "DOMICILE_REGION_ID")
    Integer domicileRegionID;
    @Column(name = "LAST_KNOWN_COORDS")
    String lastKnownCoords;
    @Column(name = "DRIVER_LOCATION_NAME")
    String driverLocationName;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH")
    Date dateOfBirth;
    @Column(name = "ADDED_BY")
    Integer addedById;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    Date dateCreated = new Date();
    @Column(name = "DRIVER_PHOTO")
    byte[] driverPhoto;
    @Column(name = "DRIVER_EMAIL")
    private String driverEmail;
    
    @Transient
    double distanceFromPickup;

    public double getDistanceFromPickup() {
        return distanceFromPickup;
    }

    public void setDistanceFromPickup(double distanceFromPickup) {
        this.distanceFromPickup = distanceFromPickup;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getDomicileRegionID() {
        return domicileRegionID;
    }

    public void setDomicileRegionID(Integer domicileRegionID) {
        this.domicileRegionID = domicileRegionID;
    }

    public String getLastKnownCoords() {
        return lastKnownCoords;
    }

    public void setLastKnownCoords(String lastKnownCoords) {
        this.lastKnownCoords = lastKnownCoords;
    }

    public String getDriverLocationName() {
        return driverLocationName;
    }

    public void setDriverLocationName(String driverLocationName) {
        this.driverLocationName = driverLocationName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAddedById() {
        return addedById;
    }

    public void setAddedById(Integer addedById) {
        this.addedById = addedById;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public byte[] getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(byte[] driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
