package backend.webservices;

import backend.classes.Product;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Path("/vleeswaren")
    @Produces(MediaType.APPLICATION_JSON)
    public String getVleeswaren() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product product : products) {
            if (product.getIsVleeswaren()) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                productBuilder.add("productID", product.getProductID());
                productBuilder.add("beschrijving", product.getBeschrijving());
                productBuilder.add("productNaam", product.getProductNaam());
                productBuilder.add("winkel", product.getWinkel().toJson());
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
        }
        JsonArray arrayBuilderProduct = arrayBuilder.build();
        return arrayBuilderProduct.toString();
    }

    @GET
    @Path("/dranken")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDranken() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product product : products) {
            if (product.getIsDrank()) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                productBuilder.add("productID", product.getProductID());
                productBuilder.add("beschrijving", product.getBeschrijving());
                productBuilder.add("productNaam", product.getProductNaam());
                productBuilder.add("winkel", product.getWinkel().toJson());
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
        }
        JsonArray arrayBuilderProduct = arrayBuilder.build();
        return arrayBuilderProduct.toString();
    }

    @GET
    @Path("/zuivel")
    @Produces(MediaType.APPLICATION_JSON)
    public String getZuivel() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product product : products) {
            if (product.getIsZuivel()) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                productBuilder.add("productID", product.getProductID());
                productBuilder.add("beschrijving", product.getBeschrijving());
                productBuilder.add("productNaam", product.getProductNaam());
                productBuilder.add("winkel", product.getWinkel().toJson());
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
        }
        JsonArray arrayBuilderProduct = arrayBuilder.build();
        return arrayBuilderProduct.toString();
    }

    @GET
    @Path("/groente")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGroenteEnFruit() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product product : products) {
            if (product.getIsGroente() || product.getIsFruit()) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                productBuilder.add("productID", product.getProductID());
                productBuilder.add("beschrijving", product.getBeschrijving());
                productBuilder.add("productNaam", product.getProductNaam());
                productBuilder.add("winkel", product.getWinkel().toJson());
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
        }
        JsonArray arrayBuilderProduct = arrayBuilder.build();
        return arrayBuilderProduct.toString();
    }

    @GET
    @Path("/koek")
    @Produces(MediaType.APPLICATION_JSON)
    public String getKoekEnSnoep() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product product : products) {
            if (product.getIsKoek() || product.getIsSnoep()) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                productBuilder.add("productID", product.getProductID());
                productBuilder.add("beschrijving", product.getBeschrijving());
                productBuilder.add("productNaam", product.getProductNaam());
                productBuilder.add("winkel", product.getWinkel().toJson());
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
        }
        JsonArray arrayBuilderProduct = arrayBuilder.build();
        return arrayBuilderProduct.toString();
    }

    @GET
    @Path("/overige")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOverige() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product product : products) {
            if (product.getIsOverige()) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                productBuilder.add("productID", product.getProductID());
                productBuilder.add("beschrijving", product.getBeschrijving());
                productBuilder.add("productNaam", product.getProductNaam());
                productBuilder.add("winkel", product.getWinkel().toJson());
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
        }
        JsonArray arrayBuilderProduct = arrayBuilder.build();
        return arrayBuilderProduct.toString();
    }
}
