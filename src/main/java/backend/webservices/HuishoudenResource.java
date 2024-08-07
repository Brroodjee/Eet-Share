package backend.webservices;

import backend.classes.*;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.StringReader;
import java.util.List;
import javax.json.JsonObject;

@Path("/huishouden")
public class HuishoudenResource extends Application {

    List<Huishouden> huishoudens = Huishouden.getHuishoudens();
    List<Invite> invites = Invite.getInvites();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHuishouden(@Context SecurityContext securityContext) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        String username = securityContext.getUserPrincipal().getName();
        System.out.println(username);
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            for (Huishouden huishouden : huishoudens) {
                boolean isInHuishouden = false;
                if (huishouden.getHoofd() != null && huishouden.getHoofd().getUsername().equals(username)) {
                    isInHuishouden = true;
                } else {
                    for (Lid lid : huishouden.getLeden()) {
                        if (lid.getUsername().equals(username)) {
                            isInHuishouden = true;
                            break;
                        }
                    }
                }

                if (isInHuishouden) {
                    JsonObjectBuilder huishoudenBuilder = Json.createObjectBuilder();
                    huishoudenBuilder.add("Hoofd", huishouden.getHoofd().toJson());
                    huishoudenBuilder.add("huishoudenNaam", huishouden.getHuishoudenNaam());

                    JsonArrayBuilder ledenArrayBuilder = Json.createArrayBuilder();
                    for (Lid lid : huishouden.getLeden()) {
                        ledenArrayBuilder.add(lid.toJson());
                    }
                    huishoudenBuilder.add("leden", ledenArrayBuilder);

                    arrayBuilder.add(huishoudenBuilder);
                }
            }
        }
        JsonArray arraybuilderHuishouden = arrayBuilder.build();
        return arraybuilderHuishouden.toString();
    }

    @POST
    @Path("/aanmaken")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("gebruiker")
    public Response addHuishouden(@Context SecurityContext securityContext, String jsonBody) {
        String username = securityContext.getUserPrincipal().getName();
        Gebruiker gebruiker = Gebruiker.getGebruikerByUsername(username);

        if (gebruiker != null && User.isValidUser(gebruiker)) {
            JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
            String huishoudenNaam = jsonObject.getString("huishoudenNaam");

            gebruiker.createHousehold(huishoudenNaam);

            JsonObjectBuilder responseObject = Json.createObjectBuilder();
            responseObject.add("Huishouden toegevoegd", huishoudenNaam);

            return Response.ok(responseObject.build()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/invite")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("hoofd")
    public Response invite(@Context SecurityContext securityContext, String jsonBody) {
        String hoofdUsername = securityContext.getUserPrincipal().getName();
        Hoofd hoofd = Hoofd.getHoofdByUsername(hoofdUsername);

        if (hoofd != null && User.isValidUser(hoofd)) {
            JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
            String gebruikerUsername = jsonObject.getString("gebruiker");

            Gebruiker gebruiker = Gebruiker.getGebruikerByUsername(gebruikerUsername);

            if (gebruiker != null) {

                Invite newInvite = new Invite(hoofd, gebruiker, "pending");

                invites.add(newInvite);

                JsonObjectBuilder responseObject = Json.createObjectBuilder();
                responseObject.add("message", "Gebruiker uitgenodigd");
                responseObject.add("hoofd", hoofd.getUsername());
                responseObject.add("gebruiker", gebruiker.getUsername());
                responseObject.add("status", newInvite.getStatusInvite());

                return Response.ok(responseObject.build()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Gebruiker niet gevonden").build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/invites")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("gebruiker")
    public String getInvite(@Context SecurityContext securityContext) {
        List<Invite> invites = Invite.getInvites();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        String username = securityContext.getUserPrincipal().getName();
        User user = User.getUserByUsername(username);

        if (user != null && User.isValidUser(user)) {
            for (Invite invite : invites) {
                if (username.equals(invite.getGebruikerInvite().getUsername()) && invite.getStatusInvite().equals("pending")) {
                    JsonObjectBuilder inviteBuilder = Json.createObjectBuilder();
                    inviteBuilder.add("hoofd", invite.getHoofdInvite().getUsername());
                    inviteBuilder.add("gebruiker", invite.getGebruikerInvite().getUsername());
                    inviteBuilder.add("status", invite.getStatusInvite());
                    arrayBuilder.add(inviteBuilder);
                }
            }
        }
        JsonArray arraybuilderHuishouden = arrayBuilder.build();
        return arraybuilderHuishouden.toString();
    }

    @POST
    @Path("/accept")
    @RolesAllowed("gebruiker")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceptInvitation(String jsonBody, @Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        Gebruiker gebruiker = Gebruiker.getGebruikerByUsername(username);

        if (gebruiker != null && User.isValidUser(gebruiker)) {
            try {
                JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
                String hoofdUsername = jsonObject.getString("gebruiker");
                System.out.println("Hoofd username from request: " + hoofdUsername);

                Hoofd hoofd = Hoofd.getHoofdByUsername(hoofdUsername);
                if (hoofd == null) {
                    System.out.println("Hoofd not found for username: " + hoofdUsername);
                    return Response.status(Response.Status.NOT_FOUND).entity("Hoofd not found").build();
                }

                Invite uitnodiging = null;
                for (Invite invite : Invite.getInvites()) {
                    if (invite.getGebruikerInvite().getUsername().equals(gebruiker.getUsername()) &&
                            invite.getHoofdInvite().getUsername().equals(hoofdUsername) &&
                            invite.getStatusInvite().equals("pending")) {
                        uitnodiging = invite;
                        break;
                    }
                }

                if (uitnodiging != null) {
                    gebruiker.acceptInvitation();
                    System.out.println("Uitnodiging geaccepteerd voor gebruiker: " + gebruiker.getUsername());
                    JsonObjectBuilder responseObject = Json.createObjectBuilder();
                    responseObject.add("message", "Uitnodiging geaccepteerd");

                    return Response.ok(responseObject.build()).build();
                } else {
                    System.out.println("Uitnodiging niet gevonden of al verwerkt voor gebruiker: " + gebruiker.getUsername());
                    return Response.status(Response.Status.NOT_FOUND).entity("Uitnodiging niet gevonden of al verwerkt").build();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Interne serverfout").build();
            }
        }
        System.out.println("Unauthorized access attempt by user: " + username);
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/decline")
    @RolesAllowed("gebruiker")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response declineInvitation(String jsonBody, @Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        Gebruiker gebruiker = Gebruiker.getGebruikerByUsername(username);

        if (gebruiker != null && User.isValidUser(gebruiker)) {
            try {
                JsonObject jsonObject = Json.createReader(new StringReader(jsonBody)).readObject();
                String hoofdUsername = jsonObject.getString("gebruiker");
                System.out.println("Hoofd username from request: " + hoofdUsername);

                Hoofd hoofd = Hoofd.getHoofdByUsername(hoofdUsername);
                if (hoofd == null) {
                    System.out.println("Hoofd not found for username: " + hoofdUsername);
                    return Response.status(Response.Status.NOT_FOUND).entity("Hoofd not found").build();
                }

                Invite uitnodiging = null;
                for (Invite invite : Invite.getInvites()) {
                    if (invite.getGebruikerInvite().getUsername().equals(gebruiker.getUsername()) &&
                            invite.getHoofdInvite().getUsername().equals(hoofdUsername) &&
                            invite.getStatusInvite().equals("pending")) {
                        uitnodiging = invite;
                        break;
                    }
                }

                if (uitnodiging != null) {
                    gebruiker.declineInvitation();
                    System.out.println("Uitnodiging geweigerd voor gebruiker: " + gebruiker.getUsername());
                    JsonObjectBuilder responseObject = Json.createObjectBuilder();
                    responseObject.add("message", "Uitnodiging geweigerd");

                    return Response.ok(responseObject.build()).build();
                } else {
                    System.out.println("Uitnodiging niet gevonden of al verwerkt voor gebruiker: " + gebruiker.getUsername());
                    return Response.status(Response.Status.NOT_FOUND).entity("Uitnodiging niet gevonden of al verwerkt").build();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Interne serverfout").build();
            }
        }
        System.out.println("Unauthorized access attempt by user: " + username);
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}