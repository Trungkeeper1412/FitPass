package com.ks.fitpass.web;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
@EntityScan("com")
@ComponentScan("com")
@SpringBootApplication
@EnableScheduling
public class FitPassWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(FitPassWebApplication.class, args);
    }
}
