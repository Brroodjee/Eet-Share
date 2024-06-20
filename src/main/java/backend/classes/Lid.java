package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

public class Lid extends User {
    private int lidID;
    private static List<Lid> leden = new ArrayList<>();

    public Lid(int lidID, String username, String password, int userID, String role) {
        super(username, password, userID, role);
        this.lidID = lidID;
        this.role = "lid";
    }

    public int getLidID() {
        return lidID;
    }

    public static List<Lid> getLeden() {
        return leden;
    }

    public static void setLeden(List<Lid> leden) {
        Lid.leden = leden;
    }

    public JsonObject toJson() {
        JsonObjectBuilder lidBuilder = Json.createObjectBuilder();
        lidBuilder.add("lidID", this.lidID);
        lidBuilder.add("naam", this.username);
        lidBuilder.add("wachtwoord", this.password);
        return lidBuilder.build();
    }
}