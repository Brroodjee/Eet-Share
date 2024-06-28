package backend.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import backend.classes.*;

import java.util.List;

@Path("/kookboek")
public class KookboekResource extends Application {

    List<Kookboek> kookboeken = Kookboek.getKookboeken();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"hoofd", "lid"})
    public String getKookboek(@Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        System.out.println(username);
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            Huishouden huishouden = Huishouden.findHuishoudenByUser(user);
            System.out.println(huishouden);
            if (huishouden != null) {
                Kookboek kookboek = Kookboek.findKookboekByHuishouden(huishouden);
                System.out.println(kookboek);
                if (kookboek != null) {
                    JsonArrayBuilder kookboekArrayBuilder = Json.createArrayBuilder();
                    for (Recept recept : kookboek.getRecepten()) {
                        JsonObjectBuilder receptBuilder = Json.createObjectBuilder();
                        receptBuilder.add("receptNaam", recept.getReceptNaam())
                                .add("servings", recept.getServings())
                                .add("cookingTime", recept.getCookingTime())
                                .add("preppingTime", recept.getPreppingTime())
                                .add("beschrijving", recept.getBeschrijving());
                        kookboekArrayBuilder.add(receptBuilder);
                        System.out.println(receptBuilder);
                    }
                    JsonObject kookboekJson = Json.createObjectBuilder()
                            .add("recepten", kookboekArrayBuilder)
                            .build();
                    return kookboekJson.toString();
                }
                throw new NotFoundException("kookboek niet gevonden");
            }
            throw new NotFoundException("huishouden niet gevonden");
        }
        throw new Error("Gebruiker niet geautoriseerd");
    }
}
