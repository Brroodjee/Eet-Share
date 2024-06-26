package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;

public class ProductQuantity implements Serializable {
    private int location;
    private Product product;
    private int quantity;

    public ProductQuantity(Product product, int quantity, int location) {
        this.product = product;
        this.quantity = quantity;
        this.location = location;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("product", product.toJson());
        builder.add("quantity", quantity);
        builder.add("location", location);
        return builder.build();
    }
}
