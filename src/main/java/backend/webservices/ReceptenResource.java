package backend.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import backend.classes.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Path("/recepten")
public class ReceptenResource extends Application {

    List<Recept> recepten = new ArrayList<>();
    List<Kookboek> kookboeken = Kookboek.getKookboeken();

    @POST
    @Path("/testing")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"hoofd", "lid"})
    public Response boopboop(@Context SecurityContext securityContext, String jsonBody) {
        JsonObject jsonObject;
        try {
            jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
        } catch (Exception e) {
            System.err.println("Error parsing JSON body: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid JSON body").build();
        }

        System.out.println("Ontvangen JSON-body:");
        System.out.println(jsonBody);

        String username = securityContext.getUserPrincipal().getName();
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            System.out.println(user);

            Huishouden huishouden = Huishouden.findHuishoudenByUser(user);
            if (huishouden != null) {
                System.out.println("daar");
                Kookboek kookboek = Kookboek.findKookboekByHuishouden(huishouden);
                System.out.println("hier");
                if (kookboek != null) {
                    System.out.println("hiero");
                    try {
                        String receptnaam = jsonObject.getString("dishName");
                        System.out.println("receptnaam: " + receptnaam);
                        int servings = Integer.parseInt(jsonObject.getString("servings"));
                        System.out.println("servings: " + servings);
                        int cookingTime = Integer.parseInt(jsonObject.getString("cookingTime"));
                        System.out.println("cookingTime: " + cookingTime);
                        int preppingTime = Integer.parseInt(jsonObject.getString("prepTime"));
                        System.out.println("prepTime: " + preppingTime);
                        String instructions = jsonObject.getString("instructions");
                        System.out.println("instructions: " + instructions);

                        Recept nieuwRecept = new Recept(receptnaam, servings, cookingTime, preppingTime, instructions);
                        recepten.add(nieuwRecept);
                        kookboek.addRecept(nieuwRecept);
                        return Response.status(Response.Status.CREATED).entity(nieuwRecept).build();
                    } catch (Exception e) {
                        System.err.println("Error reading JSON values: " + e.getMessage());
                        return Response.status(Response.Status.BAD_REQUEST).entity("Invalid data in JSON body").build();
                    }
                } else {
                    System.out.println("hier");
                    Kookboek nieuwKookboek = new Kookboek(huishouden);
                    kookboeken.add(nieuwKookboek);
                    return Response.status(Response.Status.NOT_FOUND).entity("Kookboek not found").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Household not found").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
