package org.nexus.librarymanagementsystem.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration-ms}")
  private long expirationMs;

  private SecretKey signingKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generateToken(CustomUserDetails customUserDetails) {
    return Jwts.builder()
        .subject(customUserDetails.getUsername())
        .claim("role", customUserDetails.getMember().getRole().name())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationMs))
        .signWith(signingKey())
        .compact();
  }

  private <T> T extractClaim(String token, Function<Claims, T> resolver) {
    Claims claims =
        Jwts.parser().verifyWith(signingKey()).build().parseSignedClaims(token).getPayload();
    return resolver.apply(claims);
  }

  private boolean isExpired(String token) {
    return extractClaim(token, Claims::getExpiration).before(new Date());
  }

  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean isValid(String token, CustomUserDetails userDetails) {
    String username = extractUserName(token);
    return username.equals(userDetails.getUsername()) && !isExpired(token);
  }
}
