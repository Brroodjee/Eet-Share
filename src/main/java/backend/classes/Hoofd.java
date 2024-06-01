package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Hoofd extends User {
    private int hoofdID;

    public Hoofd(int hoofdID, String naam, String email, String wachtwoord, int userID) {
        super(naam, email, wachtwoord, userID);
        this.hoofdID = hoofdID;
    }

    public int getHoofdID() {
        return hoofdID;
    }

    public void setHoofdID(int hoofdID) {
        this.hoofdID = hoofdID;
    }

    public JsonObject toJson() {
        JsonObjectBuilder hoofdBuilder = Json.createObjectBuilder();
        hoofdBuilder.add("hoofdID", this.hoofdID);
        hoofdBuilder.add("naam", this.naam);
        hoofdBuilder.add("email", this.email);
        hoofdBuilder.add("wachtwoord", this.wachtwoord);
        hoofdBuilder.add("userID", this.userID);
        return hoofdBuilder.build();
    }
}
