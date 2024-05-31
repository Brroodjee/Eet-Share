package backend.webservices;

import backend.classes.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;
import java.util.List;

@Path("/producten")
public class ProductResource extends Application {
    List<Product> products = Product.getProducten();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProducten() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product product : products) {
            JsonObjectBuilder productBuilder = Json.createObjectBuilder();
            productBuilder.add("productID", product.getProductID());
            productBuilder.add("beschrijving", product.getBeschrijving());
            productBuilder.add("productNaam", product.getProductNaam());
            productBuilder.add("winkel", product.getWinkel().toJson()); //deze specifieke regel om chatgpt gevraagd
            productBuilder.add("isVleeswaren", product.getIsVleeswaren());
            productBuilder.add("isDrank", product.getIsDrank());
            productBuilder.add("isZuivel", product.getIsZuivel());
            productBuilder.add("isGroente", product.getIsGroente());
            productBuilder.add("isFruit", product.getIsFruit());
            productBuilder.add("isKoek", product.getIsKoek());
            productBuilder.add("isSnoep", product.getIsSnoep());
            productBuilder.add("isOverige", product.getIsOverige());
            arrayBuilder.add(productBuilder);
        }
        JsonArray arrayBuilderProduct = arrayBuilder.build();
        return arrayBuilderProduct.toString();
    }
}
