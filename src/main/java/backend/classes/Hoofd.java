package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Hoofd extends User {
    private int hoofdID;
    private static List<Hoofd> hoofden = new ArrayList<>();

    public Hoofd(int hoofdID, String username, String password, int userID, String role) {
        super(username, password, userID, role);
        this.hoofdID = hoofdID;
        this.role = "hoofd";
    }

    public int getHoofdID() {
        return hoofdID;
    }

    public static int getNewHoofdID() {
        if (hoofden.isEmpty()) {
            return 1;
        } else {
            hoofden.sort(Comparator.comparingInt(Hoofd::getHoofdID).reversed());
            int highestHoofdID = hoofden.get(0).getHoofdID();
            return highestHoofdID + 1;
        }
    }

    public void setHoofdID(int hoofdID) {
        this.hoofdID = hoofdID;
    }

    public void inviteUser(Gebruiker gebruiker) {
        // Logica om een gebruiker uit te nodigen voor het huishouden
    }

    public List<User> getHouseholdMembers() {
        // Logica om huishoudleden op te halen
        return null;
    }

    public static List<Hoofd> getHoofden() {
        return hoofden;
    }

    public JsonObject toJson() {
        JsonObjectBuilder hoofdBuilder = Json.createObjectBuilder();
        hoofdBuilder.add("hoofdID", this.hoofdID);
        hoofdBuilder.add("naam", this.username);
        hoofdBuilder.add("wachtwoord", this.password);
        return hoofdBuilder.build();
    }

}
