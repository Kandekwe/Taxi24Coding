
package rw.bk.taxi24app.services;

import com.google.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.bk.taxi24app.entities.TaxiDriver;
import rw.bk.taxi24app.entities.TaxiRider;
import rw.bk.taxi24app.repository.CrudService;


@Service
public class DriverFunctions {

    @Autowired
    CrudService crudService;

    //getting all drivers
    public List<TaxiDriver> fetchAllDrivers() {
        List<TaxiDriver> allDrivers = new ArrayList<>();
        String hql = "SELECT d FROM TaxiDriver d";

        allDrivers = crudService.fetchWithHibernateQuery(hql, Collections.EMPTY_MAP);

        return allDrivers;

    }
       //getting drivers by status
    public List<TaxiDriver> fetchDriversByStatus(String status) {
        List<TaxiDriver> allDrivers = new ArrayList<>();
        String hql = "SELECT d FROM TaxiDriver d WHERE d.status =:dstatus";
        Map<String, Object> params = new HashMap<>();
        params.put("dstatus", status);

        allDrivers = crudService.fetchWithHibernateQuery(hql, params);

        return allDrivers;

    }
       //getting driverby id
    public List<TaxiDriver> fetchDriverByID(Long id) {

        String hql = "SELECT d FROM TaxiDriver d WHERE d.id =:did";
        Map<String, Object> params = new HashMap<>();
        params.put("did", id);

        List<TaxiDriver> allDrivers = crudService.fetchWithHibernateQuery(hql, params);

        return allDrivers;

    }
      //getting drivers with in a radius
    public List<TaxiDriver> fetchAvailableDriversWithinRadiusKM(LatLng pickupPoint, double radiusToCheck) {

        List<TaxiDriver> nearestDrivers = new ArrayList<>();

        double driverLatCoord = 0, driverLongCoord = 0, distanceBetween = 0;
        List<TaxiDriver> taxiDrivers = fetchDriversByStatus("A");

        String driverLastKnownLocation = "";

        for (TaxiDriver taxiDriver : taxiDrivers) {

            driverLastKnownLocation = taxiDriver.getLastKnownCoords();

            if (driverLastKnownLocation != null && !driverLastKnownLocation.trim().equalsIgnoreCase("")) {

                driverLatCoord = Double.parseDouble(driverLastKnownLocation.split(",")[0]);
                driverLongCoord = Double.parseDouble(driverLastKnownLocation.split(",")[1]);

                LatLng driverLocationPoint = new LatLng(driverLatCoord, driverLongCoord);

                distanceBetween = calculateDistanceBetweenPoints(pickupPoint, driverLocationPoint);
                taxiDriver.setDistanceFromPickup(distanceBetween);
                if (distanceBetween <= radiusToCheck) {
                    nearestDrivers.add(taxiDriver);
                }
            }

        }

        return nearestDrivers;
    }
     //getting 3 nearest drivers
     public List<TaxiDriver> fetchNearestDriversTrio(TaxiRider rider, int driverCount) {
        List<TaxiDriver> nearestDrivers = new ArrayList<>();

        double distanceBetween = 0,
                driverLatCoord = 0,
                driverLongCoord = 0,
                riderLatCoord = 0,
                riderLongCoord = 0;

        Map<Long, Double> driverDistanceMap = new HashMap<>();

        List<TaxiDriver> taxiDrivers = fetchDriversByStatus("A");

        String driverLastKnownLocation = "", riderLastKnownLocation = "";

        for (TaxiDriver taxiDriver : taxiDrivers) {

            driverLastKnownLocation = taxiDriver.getLastKnownCoords();

            if (driverLastKnownLocation != null && !driverLastKnownLocation.trim().equalsIgnoreCase("")) {

                driverLatCoord = Double.parseDouble(driverLastKnownLocation.split(",")[0]);
                driverLongCoord = Double.parseDouble(driverLastKnownLocation.split(",")[1]);

                LatLng driverLocationPoint = new LatLng(driverLatCoord, driverLongCoord);


                riderLastKnownLocation = rider.getLastKnownCoords();
                if (riderLastKnownLocation != null && !riderLastKnownLocation.trim().equalsIgnoreCase("")) {
                    riderLatCoord = Double.parseDouble(riderLastKnownLocation.split(",")[0]);
                    riderLongCoord = Double.parseDouble(riderLastKnownLocation.split(",")[1]);

                    LatLng riderLatLong = new LatLng(riderLatCoord, riderLongCoord);

                    distanceBetween = calculateDistanceBetweenPoints(riderLatLong, driverLocationPoint);

                    driverDistanceMap.put(taxiDriver.getId(), distanceBetween);

                }

            }

        }

        if (!driverDistanceMap.isEmpty()) {
            Map<Long, Double> sortedMap = driverDistanceMap.entrySet().stream().
                    sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            System.out.println("Sorted Map >> " + sortedMap);

            for (Map.Entry<Long, Double> entry : sortedMap.entrySet()) {

                if (nearestDrivers.size() < driverCount) {
                    TaxiDriver driver = fetchDriverByID(entry.getKey()).get(0);
                    driver.setDistanceFromPickup(entry.getValue());

                    nearestDrivers.add(driver);
                }

            }

        }

        return nearestDrivers;
    }

    public  double calculateDistanceBetweenPoints(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.lat;
        double lat2 = EndP.lat;
        double lon1 = StartP.lng;
        double lon2 = EndP.lng;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return Radius * c;
    }

    public void updateDriver(TaxiDriver driver) {
        crudService.saveOrUpdate(driver);
    }
}
