package com.example.taskapp;

import com.example.taskapp.entity.UserAccount;
import com.example.taskapp.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaskappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskappApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(UserAccountRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (!repo.existsByUsername("testuser")) {
                repo.save(new UserAccount("testuser", encoder.encode("password"), "USER"));
            }
            if (!repo.existsByUsername("admin")) {
                repo.save(new UserAccount("admin", encoder.encode("adminpass"), "ADMIN"));
            }
        };
    }
}