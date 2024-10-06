package com.management.user.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
    private static final String SECRET_KEY = "mySecureKeyisextremelylongyourkeyandagainbecamesolongtooolong";  // Replace with a secure key

    public static String generateJwtToken(String userName, String userRole) {
        // Define JWT claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("userRole", userRole);

        // Set expiration time for 5 days (5 days * 24 hours * 60 minutes * 60 seconds * 1000 milliseconds)
        long expirationTimeInMs = 5 * 24 * 60 * 60 * 1000;

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMs))  // 5-day expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
