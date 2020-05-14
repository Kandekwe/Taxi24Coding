
package rw.bk.taxi24app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.bk.taxi24app.entities.TaxiTrip;
import rw.bk.taxi24app.repository.CrudService;


@Service
public class TripFunctions {

    @Autowired
    CrudService crudService;

    public void createNewTrip(TaxiTrip trip) {
        trip.setId(Long.parseLong("0"));
        crudService.save(trip);

    }

    public void updateTrip(TaxiTrip trip) {

        crudService.saveOrUpdate(trip);

    }

    public List<TaxiTrip> fetchTripsByID(Long tripId) {
        List<TaxiTrip> dbTrips = new ArrayList<>();

        String hql = "SELECT t FROM TaxiTrip t WHERE t.id =:tid";
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tripId);

        dbTrips = crudService.fetchWithHibernateQuery(hql, params);

        return dbTrips;

    }

    public List<TaxiTrip> fetchTripByStatus(String tripStatus) {
        List<TaxiTrip> dbTrips = new ArrayList<>();

        String hql = "SELECT t FROM TaxiTrip t WHERE t.tripStatus =:tstatus";
        Map<String, Object> params = new HashMap<>();
        params.put("tstatus", tripStatus);

        dbTrips = crudService.fetchWithHibernateQuery(hql, params);

        return dbTrips;

    }

    public List<TaxiTrip> fetchAllTrips() {
        List<TaxiTrip> dbTrips = new ArrayList<>();

        String hql = "SELECT t FROM TaxiTrip t";

        dbTrips = crudService.fetchWithHibernateQuery(hql, Collections.EMPTY_MAP);

        return dbTrips;

    }

    public List<TaxiTrip> fetchAllActiveTrips() {
        List<TaxiTrip> dbTrips = new ArrayList<>();

        String hql = "SELECT t FROM TaxiTrip t WHERE t.tripStatus =:tstatus OR t.tripStatus =:tstatus2";
        Map<String, Object> params = new HashMap<>();
        params.put("tstatus", "Active");
        params.put("tstatus2", "Requested");

        dbTrips = crudService.fetchWithHibernateQuery(hql, params);

        return dbTrips;

    }

}
