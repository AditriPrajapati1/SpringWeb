package io.herald.SpringWeb.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JWUtil {

    private final String secret_key = "This is my supersecret key that is supersecret";

    public String generateToken(String username) {

        String token = Jwts.builder()
                .setSubject(username)
                .signWith(Keys.hmacShaKeyFor(secret_key.getBytes()))
                .compact();

        return token;
    }

    public String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(secret_key.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}