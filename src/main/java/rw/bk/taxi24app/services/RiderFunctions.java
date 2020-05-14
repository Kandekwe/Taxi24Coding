
package rw.bk.taxi24app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.bk.taxi24app.entities.TaxiRider;
import rw.bk.taxi24app.repository.CrudService;


@Service
public class RiderFunctions {

    @Autowired
    CrudService crudService;

    public List<TaxiRider> fetchAllRiders() {
        List<TaxiRider> dbRiders = new ArrayList<>();

        String hql = "SELECT r FROM TaxiRider r";

        dbRiders = crudService.fetchWithHibernateQuery(hql, Collections.EMPTY_MAP);

        return dbRiders;
    }

    public List<TaxiRider> fetchAllRiderbyID(Long id) {
        List<TaxiRider> dbRiders = new ArrayList<>();

        String hql = "SELECT r FROM TaxiRider r WHERE r.id =:rid";
        Map<String, Object> params = new HashMap<>();
        params.put("rid", id);

        dbRiders = crudService.fetchWithHibernateQuery(hql, params);

        return dbRiders;
    }
    
    public void updateRider(TaxiRider rider)
    {
        crudService.saveOrUpdate(rider);
    }
    
    

}
