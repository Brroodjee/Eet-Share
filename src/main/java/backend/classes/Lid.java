package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Lid extends User {
    private int lidID;

    public Lid(int lidID, String username,  String password) {
        super(username, password);
        this.lidID = lidID;
        this.role = "lid";
    }

    public int getLidID() {
        return lidID;
    }

    public JsonObject toJson() {
        JsonObjectBuilder lidBuilder = Json.createObjectBuilder();
        lidBuilder.add("lidID", this.lidID);
        lidBuilder.add("naam", this.username);
        lidBuilder.add("wachtwoord", this.password);
        return lidBuilder.build();
    }
}
