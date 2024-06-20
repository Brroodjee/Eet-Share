package backend.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;

public class JwtKeyProvider {
    public static final SecretKey KEY = MacProvider.generateKey(SignatureAlgorithm.HS256);
}
