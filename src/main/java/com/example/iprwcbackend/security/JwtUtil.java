//package com.example.iprwcbackend.security;
//
//import com.example.iprwcbackend.model.Account;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//
//public class JwtUtil {
//    private static final Key secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//
//    public static String generateToken(Account account) {
//        Claims claims = Jwts.claims().setSubject(account.getEmail());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(secretKey)
//                .compact();
//    }
//
//    public static String getEmailFromToken(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public static boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(secretKey).build().parse(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}