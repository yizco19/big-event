package com.zy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


// Especifica que la prueba utilizará el contexto de Spring Boot
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testSet() {
        // almacenar el contenido a redis local StringRedisTemplate
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("god", "yizco");

        // Agrega las aserciones necesarias para verificar el comportamiento esperado
        String value = ops.get("god");
        // Agrega la aserción según lo que esperas que sea el valor en Redis
        // Ejemplo: assertEquals("yizco", value);
    }
}