package backend.webservices;

import backend.classes.*;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/boodschappenlijstje")
public class BoodschappenlijstResource extends Application {


    List<Huishouden> huishoudens = Huishouden.getHuishoudens();

    @GET
    @Path("/open")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHuishouden() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Huishouden huishouden : huishoudens) {
            JsonObjectBuilder huishoudenBuilder = Json.createObjectBuilder();
            huishoudenBuilder.add("Hoofd", huishouden.getHoofd().toJson());
            huishoudenBuilder.add("huishoudenNaam", huishouden.getHuishoudenNaam());

            JsonArrayBuilder ledenArrayBuilder = Json.createArrayBuilder();
            for (Lid lid : huishouden.getLeden()) {
                ledenArrayBuilder.add(lid.toJson());
            }
            huishoudenBuilder.add("leden", ledenArrayBuilder);

            huishoudenBuilder.add("boodschappenlijstje", huishouden.getBoodschappenlijstje().toJson());

            arrayBuilder.add(huishoudenBuilder);
        }

        JsonArray huishoudensArray = arrayBuilder.build();
        return huishoudensArray.toString();
    }
}