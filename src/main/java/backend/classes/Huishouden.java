package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Huishouden implements Serializable {
    private String huishoudenNaam;
    private Hoofd hoofd;
    private List<Lid> leden;
    private Boodschappenlijstje boodschappenlijstje;

    private static List<Huishouden> huishoudens = new ArrayList<>();

    public Huishouden(String huishoudenNaam, Hoofd hoofd) {
        this.huishoudenNaam = huishoudenNaam;
        this.hoofd = hoofd;
        this.leden = new ArrayList<>();
    }

    public String getHuishoudenNaam() {
        return huishoudenNaam;
    }

    public void setHuishoudenNaam(String huishouden) {
        this.huishoudenNaam = huishouden;
    }

    public Hoofd getHoofd() {
        return hoofd;
    }

    public void setHoofd(Hoofd hoofd) {
        this.hoofd = hoofd;
    }

    public List<Lid> getLeden() {
        return leden;
    }

    public void addLid(Lid lid) {
        this.leden.add(lid);
    }

    public void removeLid(Lid lid) {
        this.leden.remove(lid);
    }

    public static List<Huishouden> getHuishoudens() {
        return huishoudens;
    }

    public static void setHuishoudens(List<Huishouden> huishoudens) {
        Huishouden.huishoudens = huishoudens;
    }

    public static Huishouden findHuishoudenByUser(User user) {
        for (Huishouden huishouden : Huishouden.getHuishoudens()) {
            if (huishouden.getHoofd().equals(user) || huishouden.getLeden().contains(user)) {
                return huishouden;
            }
        }
        return null;
    }

    public JsonObject toJson() {
        JsonObjectBuilder huishoudenBuilder = Json.createObjectBuilder();
        huishoudenBuilder.add("huishoudenNaam", this.huishoudenNaam);
        return huishoudenBuilder.build();
    }
}
