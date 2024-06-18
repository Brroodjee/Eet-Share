package backend.security;

import backend.classes.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import backend.classes.User;

import javax.crypto.SecretKey;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.Date;
import java.util.List;

@Path("/login")
public class LoginResource {

    public static final SecretKey KEY = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user){

        if (isValidUser(user)) {
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                    .claim("role", "gebruiker")
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    .compact();

            return Response.ok(new AbstractMap.SimpleEntry<>("Jwt", token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new AbstractMap.SimpleEntry<>("error", "Invalid credentials"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    private boolean isValidUser(User user) {
        List<User> users = User.getUsers();
        for (User storedUser : users) {
            if (storedUser.getUsername().equals(user.getUsername()) && storedUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
