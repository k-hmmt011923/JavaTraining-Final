package com.example.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.hellospring")
public class HellospringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellospringApplication.class, args);
    }
}
