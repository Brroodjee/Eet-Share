package backend.classes;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Huishouden implements Serializable {
    private String huishoudenNaam;
    private Hoofd hoofd;

    private ArrayList<Lid> leden;
    private ArrayList<Product> producten;

    private static List<Huishouden> huishoudens = new ArrayList<>();


    public Huishouden(String huishoudenNaam, Hoofd hoofd) {
        this.huishoudenNaam = huishoudenNaam;
        this.hoofd = hoofd;
        this.leden = new ArrayList<>();
        this.producten = new ArrayList<>();
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

    public List<Product> getProducten() {
        return producten;
    }

    public void addProduct(Product product) {
        this.producten.add(product);
    }

    public void removeProduct(Product product) {
        this.producten.remove(product);
    }

    public static List<Huishouden> getHuishoudens() {
        return huishoudens;
    }

    public static void setHuishoudens(List<Huishouden> huishoudens) {
        Huishouden.huishoudens = huishoudens;
    }

    public JsonObject toJson() {
        JsonObjectBuilder huishoudenBuilder = Json.createObjectBuilder();
        huishoudenBuilder.add("huishoudenNaam", this.huishoudenNaam);
        return huishoudenBuilder.build();
    }

}