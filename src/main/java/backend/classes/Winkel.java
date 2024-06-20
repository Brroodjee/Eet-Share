package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Winkel implements Serializable {
    private String winkelNaam;
    private int winkelID;
    private ArrayList<Product> producten;
    private static List<Winkel> winkels = new ArrayList<>();

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

    public static List<Winkel> getWinkels() {
        return winkels;
    }

    public static void setWinkels(List<Winkel> winkels) {
        Winkel.winkels = winkels;
    }

    public JsonObject toJson() {
        JsonObjectBuilder winkelBuilder = Json.createObjectBuilder();
        winkelBuilder.add("winkelNaam", this.winkelNaam);
        winkelBuilder.add("winkelID", this.winkelID);
        return winkelBuilder.build();
    }
}
