package com.example.shopeefood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShopeeFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeFoodApplication.class, args);
        System.out.println("http://localhost:8080");
    }

}
