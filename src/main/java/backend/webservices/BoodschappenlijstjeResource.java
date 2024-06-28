package backend.webservices;

import backend.classes.*;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.StringReader;
import java.util.List;

@Path("/boodschappenlijstje")
public class BoodschappenlijstjeResource extends Application {

    List<Boodschappenlijstje> boodschappenlijstjes = Boodschappenlijstje.getBoodschappenlijstjes();

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
                Boodschappenlijstje boodschappenlijstje = Boodschappenlijstje.findBoodschappenlijstjeByHuishouden(huishouden);

                if (boodschappenlijstje == null) {
                    boodschappenlijstje = new Boodschappenlijstje(huishouden);
                }

                String productNaam = jsonObject.getString("productNaam");
                int quantity = jsonObject.getInt("quantity");
                int location = jsonObject.getInt("location");

                Product product = Product.findProductByName(productNaam);
                if (product != null) {
                    boodschappenlijstje.addProduct(product, quantity, location);
                    return Response.ok(boodschappenlijstje.toJson()).build();
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
                Boodschappenlijstje boodschappenlijstje = Boodschappenlijstje.findBoodschappenlijstjeByHuishouden(huishouden);

                if (boodschappenlijstje == null) {
                    throw new NotFoundException("Boodschappenlijstje niet gevonden voor huishouden");
                }

                JsonArrayBuilder productArrayBuilder = Json.createArrayBuilder();
                for (ProductQuantity productQuantity : boodschappenlijstje.getProducten()) {
                    JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                    productBuilder.add("product", productQuantity.getProduct().toJson());
                    productBuilder.add("quantity", productQuantity.getQuantity());
                    productArrayBuilder.add(productBuilder);
                }

                JsonArray productArray = productArrayBuilder.build();
                return productArray.toString();
            }

            throw new Error("Gebruiker niet geautoriseerd");
        }

        throw new NotFoundException("Gebruiker niet gevonden");
    }
}
