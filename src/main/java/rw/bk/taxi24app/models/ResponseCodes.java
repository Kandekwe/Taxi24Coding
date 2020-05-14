
package rw.bk.taxi24app.models;

import org.springframework.stereotype.Service;


@Service("responseCodes")
public class ResponseCodes {

    public ApiResponse SUCCESS = new ApiResponse("00", "Operation Successful");
    public ApiResponse EXCEPTION_OCCURED = new ApiResponse("01", "Exception occurred");
    public ApiResponse NO_DATA = new ApiResponse("02", "No Data");
    public ApiResponse DRIVER_NOT_FOUND = new ApiResponse("04", "Driver not found or does not exist");
    public ApiResponse RIDER_NOT_FOUND = new ApiResponse("05", "Rider not found or does not exist");
    public ApiResponse TRIP_NOT_FOUND = new ApiResponse("06", "Trip not found. Try again later");
    public ApiResponse DRIVER_COUNT_MUST_NOT_BE_ZERO = new ApiResponse("07", "Driver count must not be Zero ");
    public ApiResponse TRIP_STATUS_NOT_FOUND = new ApiResponse("08", "No <TRIP_STATUS> Trip(s) found. Try again later");
    public ApiResponse NO_ACTIVE_TRIPS_FOUND = new ApiResponse("09", "No active Trip(s) found. Try again later");
     public ApiResponse DRIVERS_NOT_FOUND_FOR_LOCATION = new ApiResponse("10", "We could not find available drivers for your current location. Please try again later");

    public ApiResponse getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(ApiResponse SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public ApiResponse getEXCEPTION_OCCURED() {
        return EXCEPTION_OCCURED;
    }

    public void setEXCEPTION_OCCURED(ApiResponse EXCEPTION_OCCURED) {
        this.EXCEPTION_OCCURED = EXCEPTION_OCCURED;
    }

    public ApiResponse getNO_DATA() {
        return NO_DATA;
    }

    public void setNO_DATA(ApiResponse NO_DATA) {
        this.NO_DATA = NO_DATA;
    }

    public ApiResponse getDRIVER_NOT_FOUND() {
        return DRIVER_NOT_FOUND;
    }

    public void setDRIVER_NOT_FOUND(ApiResponse DRIVER_NOT_FOUND) {
        this.DRIVER_NOT_FOUND = DRIVER_NOT_FOUND;
    }

    public ApiResponse getRIDER_NOT_FOUND() {
        return RIDER_NOT_FOUND;
    }

    public void setRIDER_NOT_FOUND(ApiResponse RIDER_NOT_FOUND) {
        this.RIDER_NOT_FOUND = RIDER_NOT_FOUND;
    }

    public ApiResponse getTRIP_NOT_FOUND() {
        return TRIP_NOT_FOUND;
    }

    public void setTRIP_NOT_FOUND(ApiResponse TRIP_NOT_FOUND) {
        this.TRIP_NOT_FOUND = TRIP_NOT_FOUND;
    }

    public ApiResponse getDRIVER_COUNT_MUST_NOT_BE_ZERO() {
        return DRIVER_COUNT_MUST_NOT_BE_ZERO;
    }

    public void setDRIVER_COUNT_MUST_NOT_BE_ZERO(ApiResponse DRIVER_COUNT_MUST_NOT_BE_ZERO) {
        this.DRIVER_COUNT_MUST_NOT_BE_ZERO = DRIVER_COUNT_MUST_NOT_BE_ZERO;
    }

}
