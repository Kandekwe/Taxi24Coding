
package rw.bk.taxi24app.controllers;

import com.google.maps.model.LatLng;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.bk.taxi24app.entities.TaxiDriver;
import rw.bk.taxi24app.entities.TaxiRider;
import rw.bk.taxi24app.entities.TaxiTrip;
import rw.bk.taxi24app.models.ApiResponse;
import rw.bk.taxi24app.models.ResponseCodes;
import rw.bk.taxi24app.services.ConfigFunctions;
import rw.bk.taxi24app.services.DriverFunctions;
//import rw.bk.taxi24app.services.InvoiceFunctions;
import rw.bk.taxi24app.services.RiderFunctions;
import rw.bk.taxi24app.services.TripFunctions;

@RestController
@RequestMapping("/api/trips/v1")
 
public class TripsController {

    @Autowired
    ResponseCodes responseCodes;
    @Autowired
    DriverFunctions driverFunctions;
    @Autowired
    RiderFunctions riderFunctions;
    @Autowired
    TripFunctions tripFunctions;


    @Autowired
    Environment env;

    TaxiTrip trip;

    @ApiOperation(value = "Creates a new Trip Request")
    @RequestMapping(value = "/createrequest", method = RequestMethod.POST)
    public ApiResponse createNewTripRequest(
            @RequestParam(name = "driverid") String driverId,
            @RequestParam(name = "riderId") String riderId) {
        ApiResponse response = null;

        try {

            List<TaxiDriver> dbDrivers = driverFunctions.fetchDriverByID(Long.parseLong(driverId));
            List<TaxiRider> dbRiders = riderFunctions.fetchAllRiderbyID(Long.parseLong(riderId));

            if (dbDrivers.isEmpty()) {
                response = responseCodes.DRIVER_NOT_FOUND;

            } else if (dbRiders.isEmpty()) {
                response = responseCodes.RIDER_NOT_FOUND;
            } else {

                trip = new TaxiTrip(Long.parseLong(driverId), Long.parseLong(riderId), "Requested");

                tripFunctions.createNewTrip(trip);

                response = responseCodes.SUCCESS;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

    @ApiOperation(value = "Starts a created trip")
    @RequestMapping(value = "/starttrip", method = RequestMethod.POST)
    public ApiResponse startNewTrip(
            @RequestParam(name = "tripid") String tripId,
            @RequestParam(name = "pickuplat") String pickupLat,
            @RequestParam(name = "pickuplong") String pickupLong) {
        ApiResponse response = null;

        try {

            List<TaxiTrip> dbTrips = tripFunctions.fetchTripsByID(Long.parseLong(tripId));

            if (dbTrips.isEmpty()) {
                response = responseCodes.TRIP_NOT_FOUND;

            } else {

                TaxiDriver driver = driverFunctions.fetchDriverByID(dbTrips.get(0).getDriverId()).get(0);
                TaxiRider rider = riderFunctions.fetchAllRiderbyID(dbTrips.get(0).getRiderId()).get(0);

                trip = dbTrips.get(0);
                trip.setTripStartDateTime(new Date());

                trip.setPickupPointCoords(pickupLat + "," + pickupLong);
                trip.setTripStatus("Active");

                rider.setLastKnownCoords(trip.getPickupPointCoords());
                driver.setLastKnownCoords(trip.getPickupPointCoords());

                tripFunctions.updateTrip(trip);
                riderFunctions.updateRider(rider);
                driverFunctions.updateDriver(driver);

                response = responseCodes.SUCCESS;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

     @ApiOperation(value = "Completes a started trip")
    @RequestMapping(value = "/completetrip", method = RequestMethod.POST)
    public ApiResponse completeTrip(
            @RequestParam(name = "tripid") String tripId,
            @RequestParam(name = "dropofflat") String dropOffLat,
            @RequestParam(name = "dropofflong") String dropOffLong) {
        ApiResponse response = null;

        try {

            List<TaxiTrip> dbTrips = tripFunctions.fetchTripsByID(Long.parseLong(tripId));

            if (dbTrips.isEmpty()) {
                response = responseCodes.TRIP_NOT_FOUND;

            } else {

                TaxiDriver driver = driverFunctions.fetchDriverByID(dbTrips.get(0).getDriverId()).get(0);
                TaxiRider rider = riderFunctions.fetchAllRiderbyID(dbTrips.get(0).getRiderId()).get(0);

                trip = dbTrips.get(0);
                trip.setTripstopDateTime(new Date());
                trip.setDropOffCoords(dropOffLat + "," + dropOffLong);
                trip.setTripStatus("Completed");

                rider.setLastKnownCoords(trip.getDropOffCoords());
                driver.setLastKnownCoords(trip.getDropOffCoords());

                tripFunctions.updateTrip(trip);
                riderFunctions.updateRider(rider);
                driverFunctions.updateDriver(driver);


            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

    
    @ApiOperation(value = "Fetches Trip by status")
    @RequestMapping(value = "/fetchtripsbystatus", method = RequestMethod.GET)
    public ApiResponse fetchTripByStatus(
            @RequestParam(name = "tripstatus") String tripStatus
    ) {
        ApiResponse response = null;

        try {

            List<TaxiTrip> dbTrips = tripFunctions.fetchTripByStatus(tripStatus);

            if (dbTrips.isEmpty()) {

                response = responseCodes.TRIP_STATUS_NOT_FOUND;
                String errorMsg = response.getResponseDescription().replace("<TRIP_STATUS>", tripStatus);
                response.setResponseDescription(errorMsg);

            } else {

                response = responseCodes.SUCCESS;
                response.setEntity(dbTrips);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

    
     @ApiOperation(value = "Fetches all active trips")
    @RequestMapping(value = "/fetchallactivetrips", method = RequestMethod.GET)
    public ApiResponse fetchAllActiveTrips() {
        ApiResponse response = null;

        try {

            List<TaxiTrip> dbTrips = tripFunctions.fetchAllActiveTrips();

            if (dbTrips.isEmpty()) {

                response = responseCodes.NO_ACTIVE_TRIPS_FOUND;

            } else {

                response = responseCodes.SUCCESS;
                response.setEntity(dbTrips);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

}
