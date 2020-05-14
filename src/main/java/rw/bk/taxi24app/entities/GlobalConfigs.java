
package rw.bk.taxi24app.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Global configuration class to store application  Highlighted settings
 */
@Entity
@Table(name = "GLOBAL_CONFIGS")
public class GlobalConfigs implements Serializable {

    @Id
    @Column(name = "ID")
    Long id;
    @Column(name = "DRIVER_SEARCH_RADIUS")
    double driverSearchRadius;
     @Column(name = "RATE_PER_KM")
    double ratePerKm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }
    
    

    public double getDriverSearchRadius() {
        return driverSearchRadius;
    }

    public void setDriverSearchRadius(double driverSearchRadius) {
        this.driverSearchRadius = driverSearchRadius;
    }

}
