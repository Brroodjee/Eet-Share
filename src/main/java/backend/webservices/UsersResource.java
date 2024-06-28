package backend.webservices;

import backend.classes.Gebruiker;
import backend.classes.User;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/users")
public class UsersResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@Context SecurityContext securityContext) {
        List<User> users = User.getUsers();
        return Response.ok(users).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ingelogdeUser")
    public String getIngelogdeUser(@Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();

            JsonObjectBuilder userBuilder = Json.createObjectBuilder();
            userBuilder.add("role", user.getRole());

            userArrayBuilder.add(userBuilder);

            JsonArray userArray = userArrayBuilder.build();
            return userArray.toString();
        }
        throw new NotFoundException("Gebruiker niet gevonden");
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