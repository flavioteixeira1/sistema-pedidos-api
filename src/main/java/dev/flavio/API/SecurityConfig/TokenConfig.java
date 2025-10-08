package dev.flavio.API.SecurityConfig;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import dev.flavio.API.Entity.User;

@Component
public class TokenConfig {
    
    @Value("${api.security.token.secret}")
    private String secret;
    
    @Value("${api.security.token.expiration:86400}") // valor padrão 24h
    private Long expiration;

    public String generateToken(User newuser){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withClaim("userId", newuser.getId())
            .withSubject(newuser.getEmail())
            .withExpiresAt(Instant.now().plusSeconds(expiration))
            .withIssuedAt(Instant.now())
            .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            System.out.println("✅ Token válido para: " + decode.getSubject());
            System.out.println("✅ User ID: " + decode.getClaim("userId").asLong());
            
            return Optional.of(JWTUserData.builder()
                .userId(decode.getClaim("userId").asLong())
                .email(decode.getSubject())
                .build());
        }
        catch(JWTVerificationException exception){
            System.out.println("❌ Erro na validação do token: " + exception.getMessage());
            return Optional.empty();
        }
    }
}