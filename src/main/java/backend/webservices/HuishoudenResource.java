package backend.webservices;

import backend.classes.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/huishouden")
public class HuishoudenResource extends Application {

    List<Huishouden> huishoudens = Huishouden.getHuishoudens();

    @GET
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

            arrayBuilder.add(huishoudenBuilder);
        }
        JsonArray arraybuilderHuishouden = arrayBuilder.build();
        return arraybuilderHuishouden.toString();
    }
}
