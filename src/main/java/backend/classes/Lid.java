package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Lid extends User {
    private int lidID;

    public Lid(int lidID, String naam, String email, String wachtwoord, int userID) {
        super(naam, email, wachtwoord, userID);
        this.lidID = lidID;
    }

    public int getLidID() {
        return lidID;
    }

    public JsonObject toJson() {
        JsonObjectBuilder lidBuilder = Json.createObjectBuilder();
        lidBuilder.add("lidID", this.lidID);
        lidBuilder.add("naam", this.naam);
        lidBuilder.add("email", this.email);
        lidBuilder.add("wachtwoord", this.wachtwoord);
        lidBuilder.add("userID", this.userID);
        return lidBuilder.build();
    }
}
