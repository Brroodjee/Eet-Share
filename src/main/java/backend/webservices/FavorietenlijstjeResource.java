package backend.webservices;

import backend.classes.*;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Path("/favorietenlijstje")
public class FavorietenlijstjeResource {

    List<Favorietenlijstje> favorietenlijstjes = Favorietenlijstje.getFavorietenlijstjes();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"hoofd", "lid"})
    public Response boop(@Context SecurityContext securityContext, String jsonBody) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
        System.out.println("Ontvangen JSON-body:");
        System.out.println(jsonBody);

        String username = securityContext.getUserPrincipal().getName();
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            System.out.println(user);
            Favorietenlijstje userFavorietenlijstje = null;

            for (Favorietenlijstje favorietenlijstje : favorietenlijstjes) {
                if (favorietenlijstje != null && favorietenlijstje.getUserFavorietenlijstje().getUsername().equals(username)) {
                    userFavorietenlijstje = favorietenlijstje;
                    System.out.println("hieroo");
                }
            }

            if (userFavorietenlijstje == null) {
                userFavorietenlijstje = new Favorietenlijstje(user);
                userFavorietenlijstje.setProducten(new ArrayList<>()); // Initialize producten lijst
                favorietenlijstjes.add(userFavorietenlijstje);
                System.out.println("hier");
            }

            System.out.println("daar");
            String productNaam = jsonObject.getString("productNaam");
            System.out.println(productNaam);

            Product product = Product.findProductByName(productNaam);
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Product not found: " + productNaam).build();
            } else {
                userFavorietenlijstje.getProducten().add(product);
            }
            return Response.ok(userFavorietenlijstje).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/mijn")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFavorietenlijstje(@Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        System.out.println(username);
        User user = User.getUserByUsername(username);
        System.out.println(user);

        if (user != null && User.isValidUser(user)) {
            Favorietenlijstje favorietenlijstje = null;
            for (Favorietenlijstje lijstje : Favorietenlijstje.getFavorietenlijstjes()) {
                if (lijstje.getUserFavorietenlijstje().getUsername().equals(user.getUsername())) {
                    favorietenlijstje = lijstje;
                    break;
                }
            }

            if (favorietenlijstje != null) {
                JsonArrayBuilder productenArrayBuilder = Json.createArrayBuilder();
                for (Product product : favorietenlijstje.getProducten()) {
                    productenArrayBuilder.add(product.toJson());
                }
                JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
                jsonBuilder.add("producten", productenArrayBuilder);

                return jsonBuilder.build().toString();
            } else {
                throw new NotFoundException("favorietenlijstje niet gevonden");
            }
        } else {
            throw new Error("Gebruiker niet geautoriseerd");
        }
    }
}