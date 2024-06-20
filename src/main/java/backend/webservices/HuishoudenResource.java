package backend.webservices;

import backend.classes.*;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.StringReader;
import java.util.List;
import javax.json.JsonObject;

@Path("/huishouden")
public class HuishoudenResource extends Application {

    List<Huishouden> huishoudens = Huishouden.getHuishoudens();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHuishouden(@Context SecurityContext securityContext) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        String username = securityContext.getUserPrincipal().getName();
        User user = getUserByUsername(username);

        if (user != null && isValidUser(user)) {
            for (Huishouden huishouden : huishoudens) {
                boolean isInHuishouden = false;
                if (huishouden.getHoofd() != null && huishouden.getHoofd().getUsername().equals(username)) {
                    isInHuishouden = true;
                } else {
                    for (Lid lid : huishouden.getLeden()) {
                        if (lid.getUsername().equals(username)) {
                            isInHuishouden = true;
                            break;
                        }
                    }
                }

                if (isInHuishouden) {
                    JsonObjectBuilder huishoudenBuilder = Json.createObjectBuilder();
                    huishoudenBuilder.add("Hoofd", huishouden.getHoofd().toJson());
                    huishoudenBuilder.add("huishoudenNaam", huishouden.getHuishoudenNaam());

                    JsonArrayBuilder ledenArrayBuilder = Json.createArrayBuilder();
                    for (Lid lid : huishouden.getLeden()) {
                        ledenArrayBuilder.add(lid.toJson());
                    }
                    huishoudenBuilder.add("leden", ledenArrayBuilder);

                    arrayBuilder.add(huishoudenBuilder);
                }
            }
        }
        JsonArray arraybuilderHuishouden = arrayBuilder.build();
        return arraybuilderHuishouden.toString();
    }

    @POST
    @Path("/aanmaken")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("gebruiker")
    public Response addHuishouden(@Context SecurityContext securityContext, String jsonBody) {
        String username = securityContext.getUserPrincipal().getName();
        Gebruiker gebruiker = (Gebruiker) getUserByUsername(username);

        if (gebruiker != null && isValidUser(gebruiker)) {
            JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
            String huishoudenNaam = jsonObject.getString("huishoudenNaam");

            gebruiker.createHousehold(huishoudenNaam); // Roep de createHousehold methode aan
            JsonObjectBuilder responseObject = Json.createObjectBuilder();
            responseObject.add("Huishouden toegevoegd", huishoudenNaam);

            return Response.ok(responseObject.build()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    private boolean isValidUser(User user) {
        List<User> users = User.getUsers();
        for (User storedUser : users) {
            if (storedUser.getUsername().equals(user.getUsername()) && storedUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private User getUserByUsername(String username) {
        List<User> users = User.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}