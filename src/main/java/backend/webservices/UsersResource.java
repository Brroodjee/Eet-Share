package backend.webservices;

import backend.classes.Gebruiker;
import backend.classes.Hoofd;
import backend.classes.Invite;
import backend.classes.User;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.StringReader;
import java.util.List;

@Path("/users")
public class UsersResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = User.getUsers();
        return Response.ok(users).build();
    }


    @GET
    @Path("/gebruikers")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("hoofd")
    public Response getGebruikers(@Context SecurityContext securityContext) {
        List<Gebruiker> gebruikers = Gebruiker.getGebruikers();
        return Response.ok(gebruikers).build();
    }

}