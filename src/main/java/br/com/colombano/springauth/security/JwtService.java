package br.com.colombano.springauth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.duration_in_hours}")
    private long durationInHours;

    public String generateToken(String username, String email, Set<String> roles) {
        return Jwts.builder()
                   .subject(username)
                   .claim("email", email)
                   .claim("roles", roles)
                   .issuedAt(new Date())
                   .expiration(getExpirationDate())
                   .signWith(getSigningKey())
                   .compact();
    }

    private Date getExpirationDate() {
        return Date.from(Instant.now().plus(durationInHours, ChronoUnit.HOURS));
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                            .verifyWith(getSigningKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();

        return claimsResolver.apply(claims);
    }

    public Claims extractClaims(String jwt) {
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(jwt)
                   .getPayload();
    }

    public boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
