package backend.webservices;

import backend.classes.*;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.StringReader;
import java.util.List;

@Path("/productenthuis")
public class ProductenInHuisResource extends Application {

    List<ProductenInHuis> productenInHuis = ProductenInHuis.getProductenInHuis();

    private ProductenInHuis findProductenByHuishouden(Huishouden huishouden) {
        for (ProductenInHuis lijstje : ProductenInHuis.getProductenInHuis()) {
            if (lijstje.getHuishouden().equals(huishouden)) {
                return lijstje;
            }
        }
        return null;
    }

    @POST
    @Path("/testing")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"hoofd", "lid"})
    public Response boopboop(@Context SecurityContext securityContext, String jsonBody) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
        System.out.println("Ontvangen JSON-body:");
        System.out.println(jsonBody);

        String username = securityContext.getUserPrincipal().getName();
        User user = UserUtils.getUserByUsername(username);

        if (user != null && UserUtils.isValidUser(user)) {
            System.out.println(user);

            Huishouden huishouden = findHuishoudenByUser(user);
            if (huishouden != null) {
                ProductenInHuis productenInHuis = findProductenByHuishouden(huishouden);

                if (productenInHuis == null) {
                    productenInHuis = new ProductenInHuis(huishouden);
                }

                String productNaam = jsonObject.getString("productNaam");
                System.out.println(productNaam);
                int quantity = jsonObject.getInt("quantity");
                System.out.println(quantity);
                int location = jsonObject.getInt("location");
                System.out.println(location);

                Product product = findProductByName(productNaam);
                if (product != null) {
                    productenInHuis.addProduct(product, quantity, location); // Add location here
                    System.out.println(productenInHuis);
                    return Response.ok(productenInHuis.toJson()).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Household not found").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/alleProductenThuis")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlleBoodschappenlijstjes() {
        if (productenInHuis != null && !productenInHuis.isEmpty()) {
            return Response.status(Response.Status.OK).entity(productenInHuis).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Geen favorietenlijstjes gevonden").build();
        }
    }

    private Huishouden findHuishoudenByUser(User user) {
        for (Huishouden huishouden : Huishouden.getHuishoudens()) {
            if (huishouden.getHoofd().equals(user) || huishouden.getLeden().contains(user)) {
                return huishouden;
            }
        }
        return null;
    }

    public Product findProductByName(String productNaam) {
        List<Product> producten = Product.getProducten();
        for (Product product : producten) {
            if (product.getProductNaam().equalsIgnoreCase(productNaam)) {
                return product;
            }
        }
        return null;
    }

    @GET
    @Path("/ophalen")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBoodschappenlijstje(@Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        User user = UserUtils.getUserByUsername(username);

        if (user != null && UserUtils.isValidUser(user)) {
            Huishouden huishouden = findHuishoudenByUser(user);
            if (huishouden != null) {
                ProductenInHuis productenInHuis = findProductenByHuishouden(huishouden);

                if (productenInHuis == null) {
                    throw new NotFoundException("Boodschappenlijstje niet gevonden voor huishouden");
                }

                JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
                responseBuilder.add("huishoudenNaam", huishouden.getHuishoudenNaam());

                JsonArrayBuilder productArrayBuilder = Json.createArrayBuilder();
                for (ProductQuantity productQuantity : productenInHuis.getProducten()) {
                    JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                    productBuilder.add("product", productQuantity.getProduct().toJson());
                    productBuilder.add("quantity", productQuantity.getQuantity());
                    productBuilder.add("location", productQuantity.getLocation());
                    productArrayBuilder.add(productBuilder);
                }

                responseBuilder.add("producten", productArrayBuilder);

                JsonObject responseJson = responseBuilder.build();

                return responseJson.toString();
            }

            throw new Error("Gebruiker niet geautoriseerd");
        }

        throw new NotFoundException("Gebruiker niet gevonden");
    }
}
