package com.ks.fitpass.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@EntityScan("com")
@ComponentScan("com")
@SpringBootApplication
@EnableScheduling
public class FitPassWebApplication {
    public static void main(String[] args) {

        SpringApplication.run(FitPassWebApplication.class, args);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin123");
        log.info(hashedPassword);
    }
}
