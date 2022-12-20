package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/api/users")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String getAllUsers() {
        return "users";
    }

    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String getUserById(@PathVariable int id) {
        return "user %d".formatted(id);
    }

    @PostMapping("/api/users")
    public String createUser() {
        return "creating user";
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String deleteUserById(@PathVariable int id) {
        return "deleting user %d".formatted(id);
    }

}
