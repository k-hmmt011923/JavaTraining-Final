package com.example.taskapp.service;

import com.example.taskapp.entity.UserAccount;
import com.example.taskapp.repository.UserAccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository repo;

    public UserAccountService(UserAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // role は "USER" / "ADMIN" を想定 → Security側は "ROLE_USER" 形式にする
        String role = "ROLE_" + user.getRole();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );
    }
}