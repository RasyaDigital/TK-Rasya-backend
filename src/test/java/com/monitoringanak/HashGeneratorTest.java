package com.monitoringanak;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGeneratorTest {

    @Test
    public void generateHash() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("admin123");
        System.out.println("GENERATED_HASH_START");
        System.out.println(hash);
        System.out.println("GENERATED_HASH_END");
    }
}
