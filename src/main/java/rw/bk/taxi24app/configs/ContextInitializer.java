
package rw.bk.taxi24app.configs;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * Initialization of application-wide with different system environment variables
 */
@Component
public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext c) {
       
        System.setProperty("engine.name", "taxi-api");
        System.setProperty("log.target", "File");
        //application needs to be started with this argument
        System.setProperty("log.target.path", 
                System.getProperty("logging.file") == null ? "logs/" : System.getProperty("logging.file"));

        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            System.setProperty("engine.host", ip.getHostAddress());
        } catch (UnknownHostException ex) {
            System.setProperty("engine.host", "UKNOWN");
        }
    }

}
