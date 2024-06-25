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

    private Boodschappenlijstje findBoodschappenlijstjeByHuishouden(Huishouden huishouden) {
        for (Boodschappenlijstje lijstje : Boodschappenlijstje.getBoodschappenlijstjes()) {
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
                Boodschappenlijstje boodschappenlijstje = findBoodschappenlijstjeByHuishouden(huishouden);

                if (boodschappenlijstje == null) {
                    boodschappenlijstje = new Boodschappenlijstje(huishouden);
                }

                String productNaam = jsonObject.getString("productNaam");
                int quantity = jsonObject.getInt("quantity");

                Product product = findProductByName(productNaam);
                if (product != null) {
                    String category = product.getCategory();

                    boodschappenlijstje.addProduct(product, quantity);
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
    @Path("/alleBoodschappenlijstjes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlleBoodschappenlijstjes() {

        if (boodschappenlijstjes != null && !boodschappenlijstjes.isEmpty()) {
            return Response.status(Response.Status.OK).entity(boodschappenlijstjes).build();
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
                Boodschappenlijstje boodschappenlijstje = findBoodschappenlijstjeByHuishouden(huishouden);

                if (boodschappenlijstje == null) {
                    throw new NotFoundException("Boodschappenlijstje niet gevonden voor huishouden");
                }

                JsonArrayBuilder productArrayBuilder = Json.createArrayBuilder();
                for (ProductQuantity productQuantity : boodschappenlijstje.getProducten()) {
                    JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                    productBuilder.add("product", productQuantity.getProduct().toJson()); // Voeg hele product toe
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
