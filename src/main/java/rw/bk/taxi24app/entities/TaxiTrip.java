
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
@Table(name = "TAXI_TRIP")
public class TaxiTrip implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "DRIVER_ID")
    Long driverId;
    @Column(name = "RIDER_ID")
    Long riderId;
    @Column(name = "TRIP_REQUEST_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date tripRequestTime = new Date();
    @Column(name = "TRIP_START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date tripStartDateTime;
    @Column(name = "TRIP_END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date tripstopDateTime;
    @Column(name = "COMMENTS")
    String tripComments;
    @Column(name = "TRIP_RATING")
    int tripRating;
    @Column(name = "PICKUP_COORDS")
    String pickupPointCoords;
    @Column(name = "PICKUP_GEONAME")
    String pickupGeoName;
    @Column(name = "DROPOFF_COORDS")
    String dropOffCoords;
    @Column(name = "DROPOFF_GEONAME")
    String dropOffGeoName;
    @Column(name = "TRIP_STATUS")
    String tripStatus; //Requested / Complete / Cancelled or failed

    public TaxiTrip(
            Long driverId, 
            Long riderId,
            String tripStatus) {
        this.driverId = driverId;
        this.riderId = riderId;
        this.tripStatus = tripStatus;
    }

    public TaxiTrip() {
    }




    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getDropOffCoords() {
        return dropOffCoords;
    }

    public void setDropOffCoords(String dropOffCoords) {
        this.dropOffCoords = dropOffCoords;
    }

    public String getPickupPointCoords() {
        return pickupPointCoords;
    }

    public void setPickupPointCoords(String pickupPointCoords) {
        this.pickupPointCoords = pickupPointCoords;
    }

    public String getPickupGeoName() {
        return pickupGeoName;
    }

    public void setPickupGeoName(String pickupGeoName) {
        this.pickupGeoName = pickupGeoName;
    }

    public String getDropOffGeoName() {
        return dropOffGeoName;
    }

    public void setDropOffGeoName(String dropOffGeoName) {
        this.dropOffGeoName = dropOffGeoName;
    }

    public String getTripComments() {
        return tripComments;
    }

    public void setTripComments(String tripComments) {
        this.tripComments = tripComments;
    }

    public int getTripRating() {
        return tripRating;
    }

    public void setTripRating(int tripRating) {
        this.tripRating = tripRating;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    public Date getTripStartDateTime() {
        return tripStartDateTime;
    }

    public void setTripStartDateTime(Date tripStartDateTime) {
        this.tripStartDateTime = tripStartDateTime;
    }

    public Date getTripstopDateTime() {
        return tripstopDateTime;
    }

    public void setTripstopDateTime(Date tripstopDateTime) {
        this.tripstopDateTime = tripstopDateTime;
    }

     public Date getTripRequestTime() {
        return tripRequestTime;
    }

    public void setTripRequestTime(Date tripRequestTime) {
        this.tripRequestTime = tripRequestTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
