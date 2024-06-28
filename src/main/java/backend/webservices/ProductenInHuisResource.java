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
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            System.out.println(user);

            Huishouden huishouden = Huishouden.findHuishoudenByUser(user);
            if (huishouden != null) {
                ProductenInHuis productenInHuis = ProductenInHuis.findProductenByHuishouden(huishouden);

                if (productenInHuis == null) {
                    productenInHuis = new ProductenInHuis(huishouden);
                }

                String productNaam = jsonObject.getString("productNaam");
                System.out.println(productNaam);
                int quantity = jsonObject.getInt("quantity");
                System.out.println(quantity);
                int location = jsonObject.getInt("location");
                System.out.println(location);

                Product product = Product.findProductByName(productNaam);
                if (product != null) {
                    productenInHuis.addProduct(product, quantity, location);
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
    @Path("/ophalen")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBoodschappenlijstje(@Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            Huishouden huishouden = Huishouden.findHuishoudenByUser(user);
            if (huishouden != null) {
                ProductenInHuis productenInHuis = ProductenInHuis.findProductenByHuishouden(huishouden);

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
