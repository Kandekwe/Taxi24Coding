
package rw.bk.taxi24app.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  Configuration class to store notification  settings
 * 
 */
@Entity
@Table(name = "NOTIFICATION_CONFIG")
public class NotificationConfig implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMAIL_SERVER")
    private String emailServer;
    @Column(name = "FROM_EMAIL")
    private String fromEmail;
    @Column(name = "EMAIL_PASS")
    private String emailPass;
    @Column(name = "EMAIL_PORT")
    private int emailPort;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailServer() {
        return emailServer;
    }

    public void setEmailServer(String emailServer) {
        this.emailServer = emailServer;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public int getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(int emailPort) {
        this.emailPort = emailPort;
    }

    public String getEmailPass() {
        return emailPass;
    }

    public void setEmailPass(String emailPass) {
        this.emailPass = emailPass;
    }
    
    
    
    

}
