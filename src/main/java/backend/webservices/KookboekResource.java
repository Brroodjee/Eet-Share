package backend.webservices;

import backend.classes.Huishouden;
import backend.classes.Kookboek;
import backend.classes.Recept;
import backend.classes.User;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
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
        User user = UserUtils.getUserByUsername(username);

        if (user != null && UserUtils.isValidUser(user)) {
            Huishouden huishouden = findHuishoudenByUser(user);
            System.out.println(huishouden);
            if (huishouden != null) {
                Kookboek kookboek = findKookboekByHuishouden(huishouden);
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

    private Huishouden findHuishoudenByUser(User user) {
        for (Huishouden huishouden : Huishouden.getHuishoudens()) {
            if (huishouden.getHoofd().equals(user) || huishouden.getLeden().contains(user)) {
                return huishouden;
            }
        }
        return null;
    }

    private Kookboek findKookboekByHuishouden(Huishouden huishouden) {

        for (Kookboek kookboek : kookboeken) {
            if (kookboek.getHuishouden().equals(huishouden)) {
                return kookboek;
            }
        }
        return null;
    }
}
