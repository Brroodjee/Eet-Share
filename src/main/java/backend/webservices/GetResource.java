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
import java.io.StringReader;
import java.util.List;

@Path("/login")
public class GetResource extends Application {
    List<User> users = User.getUsers();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUsers(String jsonBody) {
        JsonObjectBuilder responseObject = Json.createObjectBuilder();
        System.out.println(jsonBody);
        try {
            JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
            System.out.println(jsonObject);
            String naam = jsonObject.getString("naam");
            String email = jsonObject.getString("email");
            String wachtwoord = jsonObject.getString("wachtwoord");
            int userID = User.getNewUserID();

            User user = new User(naam, email, wachtwoord, userID);
            responseObject.add("user toegevoegd met userID:", userID);
        } catch (Exception e) {
            responseObject.add("error", e.getMessage());
        }

        return responseObject.build().toString();
    }
}