package backend.classes;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

public class ProductenInHuis {
    private Huishouden huishouden;
    private ArrayList<ProductQuantity> producten;
    private static List<ProductenInHuis> productenInHuis = new ArrayList<>();

    public ProductenInHuis(Huishouden huishouden) {
        this.huishouden = huishouden;
        this.producten = new ArrayList<>();
        productenInHuis.add(this);
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

    public static List<ProductenInHuis> getProductenInHuis() {
        return productenInHuis;
    }

    public static void setProductenInHuis(List<ProductenInHuis> productenInHuis) {
        ProductenInHuis.productenInHuis = productenInHuis;
    }

    public void addProduct(Product product, int quantity, int location) {
        for (ProductQuantity pq : producten) {
            if (pq.getProduct().equals(product)) {
                pq.setQuantity(pq.getQuantity() + quantity);
                pq.setLocation(location); // Update location if product already exists
                return;
            }
        }
        producten.add(new ProductQuantity(product, quantity, location));
    }

    public static ProductenInHuis findProductenByHuishouden(Huishouden huishouden) {
        for (ProductenInHuis lijstje : ProductenInHuis.getProductenInHuis()) {
            if (lijstje.getHuishouden().equals(huishouden)) {
                return lijstje;
            }
        }
        return null;
    }

    public JsonObject toJson() {
        JsonObjectBuilder productenInHuisBuilder = Json.createObjectBuilder();
        productenInHuisBuilder.add("huishoudenNaam", this.huishouden.getHuishoudenNaam());

        JsonArrayBuilder productenArrayBuilder = Json.createArrayBuilder();
        for (ProductQuantity productQuantity : this.producten) {
            productenArrayBuilder.add(productQuantity.toJson());
        }
        productenInHuisBuilder.add("producten", productenArrayBuilder);

        return productenInHuisBuilder.build();
    }
}
