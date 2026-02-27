package com.example.taskapp.entity;

import jakarta.persistence.*;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // "USER" or "ADMIN" を想定（DBにはプレフィックス無しで持つ）
    @Column(nullable = false)
    private String role;

    protected UserAccount() {}

    public UserAccount(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}