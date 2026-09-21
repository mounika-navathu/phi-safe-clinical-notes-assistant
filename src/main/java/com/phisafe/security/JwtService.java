package com.phisafe.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class JwtService {
  private final SecretKey key; private final long ttlMin;
  public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.ttl-minutes}") long ttl){
    key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); ttlMin=ttl; }
  public String issue(String user,Collection<String> roles){
    Instant now=Instant.now();
    return Jwts.builder().subject(user).claim("roles",roles).issuedAt(Date.from(now))
      .expiration(Date.from(now.plusSeconds(ttlMin*60))).signWith(key).compact();
  }
  public Claims parse(String token){ return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload(); }
}