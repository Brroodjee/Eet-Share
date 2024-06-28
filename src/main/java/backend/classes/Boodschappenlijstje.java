package backend.classes;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Boodschappenlijstje implements Serializable {
    private Huishouden huishouden;
    private ArrayList<ProductQuantity> producten;
    private static List<Boodschappenlijstje> boodschappenlijstjes = new ArrayList<>();

    public Boodschappenlijstje(Huishouden huishouden) {
        this.huishouden = huishouden;
        this.producten = new ArrayList<>();
        boodschappenlijstjes.add(this);
    }

    public Huishouden getHuishouden() {
        return huishouden;
    }

    public ArrayList<ProductQuantity> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<ProductQuantity> producten) {
        this.producten = producten;
    }

    public static List<Boodschappenlijstje> getBoodschappenlijstjes() {
        return boodschappenlijstjes;
    }

    public static void setBoodschappenlijstjes(List<Boodschappenlijstje> boodschappenlijstjes) {
        Boodschappenlijstje.boodschappenlijstjes = boodschappenlijstjes;
    }

    public void addProduct(Product product, int quantity, int location) {
        for (ProductQuantity pq : producten) {
            if (pq.getProduct().equals(product)) {
                pq.setQuantity(pq.getQuantity() + quantity);
                pq.setLocation(location);
                return;
            }
        }
        producten.add(new ProductQuantity(product, quantity, location));
    }

    public static Boodschappenlijstje findBoodschappenlijstjeByHuishouden(Huishouden huishouden) {
        for (Boodschappenlijstje lijstje : Boodschappenlijstje.getBoodschappenlijstjes()) {
            if (lijstje.getHuishouden().equals(huishouden)) {
                return lijstje;
            }
        }
        return null;
    }

    public JsonObject toJson() {
        JsonObjectBuilder boodschappenlijstjeBuilder = Json.createObjectBuilder();
        boodschappenlijstjeBuilder.add("huishoudenNaam", this.huishouden.getHuishoudenNaam());

        JsonArrayBuilder productenArrayBuilder = Json.createArrayBuilder();
        for (ProductQuantity productQuantity : this.producten) {
            productenArrayBuilder.add(productQuantity.toJson());
        }
        boodschappenlijstjeBuilder.add("producten", productenArrayBuilder);

        return boodschappenlijstjeBuilder.build();
    }
}
