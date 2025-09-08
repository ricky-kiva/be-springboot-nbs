package com.rickyslash.nbs.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
  private static String JWT_SECRET;
  private static int JWT_EXPIRY;

  public JwtUtil(
      @Value("${app.jwt.secret}") String jwtSecret,
      @Value("${app.jwt.expiry}") int jwtExpiry
  ) {
    JWT_SECRET = jwtSecret;
    JWT_EXPIRY = jwtExpiry;
  }

  public static String generateToken(UserDetails user) {
    return Jwts
        .builder()
        .subject(user.getUsername())
        .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRY))
        .signWith(getSigningKey())
        .compact();
  }

  public static Claims getClaims(String token) {
    return Jwts
        .parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
  
  public static boolean isTokenValid(String token) {
    return !isExpired(token);
  }

  private static boolean isExpired(String token) {
    return getClaims(token)
        .getExpiration()
        .before(new Date());
  }

  private static SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
