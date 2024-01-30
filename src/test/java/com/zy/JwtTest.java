package com.zy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "zhangsan");
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256("yizco")); // generar token usando HMAC256

        System.out.println(token);
    }


   /* @Test
    public void testParse() {
        // simular token de usuario con nombre de usuario "zhangsan"
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJuYW1lIjoiemhhbmdzYW4ifSwiZXhwIjoxNzA2NDE4OTc5fQ.TZ8bcxTeRfxwivnxEsW7r6_rOq6X8g5UEqbcJ3ote5c\n";
        // verificar token utilizando el mismo algoritmo utilizado para la firma
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("yizco")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        // recuperar reclamos
        Map<String, Claim> claims = decodedJWT.getClaims();
        Claim userClaim = claims.get("user");
        String username = userClaim.asMap().get("username").toString();

        System.out.println("Username: " + username);
    }*/
}
