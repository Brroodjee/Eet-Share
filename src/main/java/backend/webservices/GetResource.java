package backend.webservices;

import backend.classes.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("/login")
public class GetResource extends Application {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        List<User> users = User.getUsers();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (User user : users) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("naam", user.getNaam());
            jsonObjectBuilder.add("email", user.getEmail());
            jsonObjectBuilder.add("wachtwoord", user.getWachtwoord());
            jsonObjectBuilder.add("userID", user.getUserID());
            jsonArrayBuilder.add(jsonObjectBuilder);
        }
        JsonArray jsonArray = jsonArrayBuilder.build();
        return jsonArray.toString();
    }
}