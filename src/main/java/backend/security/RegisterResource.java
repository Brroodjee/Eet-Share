package backend.security;

import backend.classes.Gebruiker;
import backend.classes.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

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

@Path("/register")
public class RegisterResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Gebruiker user) {
        List<User> users = User.getUsers();

        if (User.exists(user.getUsername())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Gebruikersnaam is al in gebruik")
                    .build();
        }

        users.add(user);
        List<Gebruiker> gebruikers = Gebruiker.getGebruikers();
        gebruikers.add(user);

        String role = "gebruiker";

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, JwtKeyProvider.KEY)
                .compact();

        return Response.ok(new AbstractMap.SimpleEntry<>("Jwt", token)).build();
    }
}
