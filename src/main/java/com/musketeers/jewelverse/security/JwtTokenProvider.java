package com.musketeers.jewelverse.security;

import com.musketeers.jewelverse.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfig.getExpiration());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String refreshToken(String token) {
        Claims claims = Jwts.parserBuilder() // This should now work with a recent jjwt version
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token) // Use parseClaimsJws instead of parseSignedClaims
                .getBody();
        String username = claims.getSubject();
        return generateToken(username);
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder() // This should now work with a recent jjwt version
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token) // Use parseClaimsJws instead of parseSignedClaims
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder() // This should now work with a recent jjwt version
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token); // Use parseClaimsJws instead of parseSignedClaims
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}