package backend.security;

import backend.classes.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestCtx) {
        String scheme = requestCtx.getUriInfo().getRequestUri().getScheme();
        MySecurityContext msc = null;

        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer".length()).trim();
            try {
                JwtParser parser = Jwts.parser().setSigningKey(JwtKeyProvider.KEY);
                Claims claims = parser.parseClaimsJws(token).getBody();

                String username = claims.getSubject();
                List<User> users = User.getUsers();
                for (User storedUser : users) {
                    if (storedUser.getUsername().equals(username)) {
                        msc = new MySecurityContext(storedUser, scheme);
                        break;
                    }
                }
            } catch (JwtException | IllegalArgumentException e) {
                System.out.println("Invalid JWT: " + e.getMessage());
            }
        }

        if (msc == null) {
            msc = new MySecurityContext(null, scheme);
        }

        requestCtx.setSecurityContext(msc);
    }
}
