package com.siddhi.taskmanagement.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key (must be at least 256 bits for HS256)
    private final String SECRET = "ThisIsASecretKeyForJwtTokenGeneration12345";

    // Token validity (1 hour)
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ================= GENERATE TOKEN =================
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ================= EXTRACT EMAIL =================
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // ================= EXTRACT ROLE =================
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // ================= VALIDATE TOKEN =================
    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ================= COMMON CLAIM PARSER =================
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
