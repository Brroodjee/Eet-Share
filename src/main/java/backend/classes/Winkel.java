package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

public class Winkel {
    private String winkelNaam;
    private int winkelID;
    private ArrayList<Product> producten;

    public Winkel(String winkelNaam, int winkelID) {
        this.winkelNaam = winkelNaam;
        this.winkelID = winkelID;
    }

    public String getWinkelNaam() {
        return winkelNaam;
    }

    public int getWinkelID() {
        return winkelID;
    }

    public JsonObject toJson() {
        JsonObjectBuilder winkelBuilder = Json.createObjectBuilder();
        winkelBuilder.add("winkelNaam", this.winkelNaam);
        winkelBuilder.add("winkelID", this.winkelID);
        return winkelBuilder.build();
    }
}
