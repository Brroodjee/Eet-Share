package backend.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("eet-share")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("backend.webservices", "backend.security");
        register(RolesAllowedDynamicFeature.class);
        register(CorsFilter.class);
    }
}
