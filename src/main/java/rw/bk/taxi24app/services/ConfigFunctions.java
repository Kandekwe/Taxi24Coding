
package rw.bk.taxi24app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.bk.taxi24app.entities.GlobalConfigs;
import rw.bk.taxi24app.repository.CrudService;

@Service
public class ConfigFunctions {

    @Autowired
    CrudService crudService;

    public List<GlobalConfigs> fetchConfigs() {
        List<GlobalConfigs> allConfigs = new ArrayList<>();
        String hql = "SELECT g FROM GlobalConfigs g";

        allConfigs = crudService.fetchWithHibernateQuery(hql, Collections.EMPTY_MAP);

        return allConfigs;

    }

}
