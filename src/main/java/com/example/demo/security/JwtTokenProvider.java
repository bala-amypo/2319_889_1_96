package com.example.demo.security;

import com.example.demo.model.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private String jwtSecret = "mySecretKey123456789012345678901234567890";
    private int jwtExpirationMs = 86400000; // 24 hours

    public JwtTokenProvider() {
        // No-arg constructor for tests
    }

    public String generateToken(UserAccount user) {
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationMs);
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmail(String token) {
        Claims claims = getClaims(token);
        return claims.get("email", String.class);
    }

    public String getRole(String token) {
        Claims claims = getClaims(token);
        return claims.get("role", String.class);
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        Object userIdClaim = claims.get("userId");
        if (userIdClaim instanceof Integer) {
            return ((Integer) userIdClaim).longValue();
        }
        return (Long) userIdClaim;
    }

    private Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}