
package rw.bk.taxi24app.controllers;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.bk.taxi24app.entities.TaxiDriver;
import rw.bk.taxi24app.entities.TaxiRider;
import rw.bk.taxi24app.models.ApiResponse;
import rw.bk.taxi24app.models.ResponseCodes;
import rw.bk.taxi24app.services.DriverFunctions;
import rw.bk.taxi24app.services.RiderFunctions;


@RestController
@RequestMapping("/api/riders/v1")
public class RidersController {

    @Autowired
    RiderFunctions riderFunctions;
    @Autowired
    DriverFunctions driverFunctions;
    @Autowired
    ResponseCodes responseCodes;

    
    @ApiOperation(value = "Fetches all riders")
    @RequestMapping(value = "/fetchall", method = RequestMethod.GET)
    public ApiResponse fetchAllRiders() {
        ApiResponse response = null;

        try {

            List<TaxiRider> allRiders = riderFunctions.fetchAllRiders();

            if (allRiders.isEmpty()) {
                response = responseCodes.NO_DATA;

            } else {
                response = responseCodes.SUCCESS;
                response.setEntity(allRiders);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

     @ApiOperation(value = "Fetches rider by ID")
    @RequestMapping(value = "/fetchbyid", method = RequestMethod.GET)
    public ApiResponse fetchRidersById(@RequestParam(name = "id") Long id) {
        ApiResponse response = null;

        try {

            List<TaxiRider> taxiRiders = riderFunctions.fetchAllRiderbyID(id);

            if (taxiRiders.isEmpty()) {
                response = responseCodes.RIDER_NOT_FOUND;

            } else {
                response = responseCodes.SUCCESS;
                response.setEntity(taxiRiders.get(0));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

    @ApiOperation(value = "Fetches nearest drivers  by rider id. Filters by drivercount parameter")
    @RequestMapping(value = "/fetchdriversbyrider", method = RequestMethod.GET)
    public ApiResponse fetchNearestRidersByCount(
            @RequestParam(name = "riderid") Long riderId,
            @RequestParam(name = "drivercount") int driverCount) {
        ApiResponse response = null;

        try {

            List<TaxiRider> taxiRiders = riderFunctions.fetchAllRiderbyID(riderId);

            if (taxiRiders.isEmpty()) {
                response = responseCodes.RIDER_NOT_FOUND;

            } else if (driverCount < 1) {
                response = responseCodes.DRIVER_COUNT_MUST_NOT_BE_ZERO;
            } else {

               

                TaxiRider dbRider = taxiRiders.get(0);
                List<TaxiDriver> nearestDrivers = driverFunctions.fetchNearestDriversTrio(dbRider, driverCount);
                
                if(nearestDrivers.isEmpty())
                {
                     response = responseCodes.DRIVERS_NOT_FOUND_FOR_LOCATION;
                }
                else{
                     response = responseCodes.SUCCESS;
                     response.setEntity(nearestDrivers);
                }

               

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

}
