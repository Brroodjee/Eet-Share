package backend;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("eet-share")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("backend.webservices");
    }
}