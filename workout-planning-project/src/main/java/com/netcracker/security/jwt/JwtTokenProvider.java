package com.netcracker.security.jwt;

import com.auth0.jwt.JWT;
import com.netcracker.security.details.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
@Data
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public static class JwtProperties {
        public static final String SECRET = "SomeSecretForJWTGeneration";
        public static final int EXPIRATION_TIME = 864_000_000; // 10 days
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
    }

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        return JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            logger.info("Hello "+ authToken);
            Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return JWT.require(HMAC512(JwtProperties.SECRET))
                .build()
                .verify(token)
                .getSubject();
    }
}
