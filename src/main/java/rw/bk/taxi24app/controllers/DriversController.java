
package rw.bk.taxi24app.controllers;

import com.google.maps.model.LatLng;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.bk.taxi24app.entities.GlobalConfigs;
import rw.bk.taxi24app.entities.TaxiDriver;
import rw.bk.taxi24app.models.ApiResponse;
import rw.bk.taxi24app.models.ResponseCodes;
import rw.bk.taxi24app.services.ConfigFunctions;
import rw.bk.taxi24app.services.DriverFunctions;

@RestController
@RequestMapping("/api/taxidrivers/v1")
public class DriversController {

    @Autowired
    ResponseCodes responseCodes;
    @Autowired
    DriverFunctions driverFunctions;
    @Autowired
    ConfigFunctions configFunctions;

    @ApiOperation(value = "Fetches taxi driver by ID")
    @RequestMapping(value = "/fetchbyid", method = RequestMethod.GET)
    public ApiResponse fetchDriverById(@RequestParam(name = "id") Long id) {
        ApiResponse response = null;

        try {

            List<TaxiDriver> taxiDrivers = driverFunctions.fetchDriverByID(id);

            if (taxiDrivers.isEmpty()) {
                response = responseCodes.DRIVER_NOT_FOUND;

            } else {
                response = responseCodes.SUCCESS;
                response.setEntity(taxiDrivers);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

     @ApiOperation(value = "Fetches all taxi drivers")
    @RequestMapping(value = "/fetchall", method = RequestMethod.GET)
    public ApiResponse fetchAllDrivers() {
        ApiResponse response = null;

        try {

            List<TaxiDriver> allTaxiDrivers = driverFunctions.fetchAllDrivers();

            if (allTaxiDrivers.isEmpty()) {
                response = responseCodes.NO_DATA;

            } else {
                response = responseCodes.SUCCESS;
                response.setEntity(allTaxiDrivers);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

    @ApiOperation(value = "Fetches all available taxi drivers")
    @RequestMapping(value = "/fetchavailable", method = RequestMethod.GET)
    public ApiResponse fetchAllAvailableDrivers() {
        ApiResponse response = null;

        try {

            List<TaxiDriver> allTaxiDrivers = driverFunctions.fetchDriversByStatus("A");

            if (allTaxiDrivers.isEmpty()) {
                response = responseCodes.NO_DATA;
                response.setEntity(allTaxiDrivers);
            } else {
                response = responseCodes.SUCCESS;
                response.setEntity(allTaxiDrivers);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
        }

        return response;
    }

    @ApiOperation(value = "Fetches nearest drivers by location coordinates")
    @RequestMapping(value = "/fetchnearestbylocation", method = RequestMethod.GET)
    public ApiResponse fetchAvailableDriversByLocation(
            @RequestParam(name = "longitude") double longitude,
            @RequestParam(name = "latitude") double latitude
    ) {
        ApiResponse response = null;

        try {

            LatLng customLocation;
            customLocation = new LatLng(latitude, longitude);  // Create a custom LatLng Object for location search

            List<GlobalConfigs> configs;
            configs = configFunctions.fetchConfigs(); //Fetch global config 

            List<TaxiDriver> allTaxiDrivers = new ArrayList<>();

            if (configs.isEmpty()) { // I
                allTaxiDrivers = driverFunctions.fetchAvailableDriversWithinRadiusKM(customLocation,
                        3); //If no config exists, Default TO 3 KM radius
            } else {

                GlobalConfigs config = configs.get(0);

                if (config.getDriverSearchRadius() > 0) {
                    allTaxiDrivers = driverFunctions.fetchAvailableDriversWithinRadiusKM(customLocation,
                            config.getDriverSearchRadius());

                } else { //If radius config not set, default to 3KM radius
                    allTaxiDrivers = driverFunctions.fetchAvailableDriversWithinRadiusKM(customLocation,
                            3);
                }

            }

            if (allTaxiDrivers.isEmpty()) {
                response = responseCodes.NO_DATA;
                response.setEntity(allTaxiDrivers);
            } else {
                response = responseCodes.SUCCESS;
                response.setEntity(allTaxiDrivers);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response = responseCodes.EXCEPTION_OCCURED;
            response.setEntity(new String(ex.getMessage()));
        }

        return response;
    }

}
