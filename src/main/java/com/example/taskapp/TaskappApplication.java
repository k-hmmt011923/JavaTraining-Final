package com.example.taskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.taskapp")
public class TaskappApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskappApplication.class, args);
    }
}