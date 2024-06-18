package backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import backend.classes.User;
import backend.classes.Gebruiker;

import javax.crypto.SecretKey;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.Date;
import java.util.List;

@Path("/register")
public class RegisterResource {

    public static final SecretKey KEY = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Gebruiker user) {
        List<User> users = User.getUsers();
        users.add(user);

        String role = "gebruiker";

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();

        return Response.ok(new AbstractMap.SimpleEntry<>("Jwt", token)).build();
    }
}
