package com.alura.forohub.security;

import com.alura.forohub.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
  @Value("${api.security.secret}")
  private String apiSecret;

  public String generarToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      return JWT.create()
          .withIssuer("forohub")
          .withSubject(user.getUsername())
          .withClaim("id", user.getId())
          .withExpiresAt(generarFechaExpiracion())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error al generar token JWT", exception);
    }
  }

  private Instant generarFechaExpiracion() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
  }

  public String getSubject(String token) {
    if (token == null) {
      throw new RuntimeException("Token cannot be null");
    }
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      JWTVerifier verifier = JWT.require(algorithm).withIssuer("forohub").build();
      DecodedJWT jwt = verifier.verify(token);
      return jwt.getSubject();
    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Token JWT inválido o expirado");
    }
  }
}
