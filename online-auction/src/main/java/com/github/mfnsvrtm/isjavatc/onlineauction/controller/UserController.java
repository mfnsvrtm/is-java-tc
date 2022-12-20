package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/api/users")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String listUsers() {
        return "users";
    }

    @GetMapping("/api/users/{userId}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String getUser(@PathVariable int userId) {
        return "user %d".formatted(userId);
    }

    @PostMapping("/api/users/register")
    public String registerUser() {
        return "registering user";
    }

    @DeleteMapping("/api/users/{userId}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String deleteUser(@PathVariable int userId) {
        return "deleting user %d".formatted(userId);
    }

}
