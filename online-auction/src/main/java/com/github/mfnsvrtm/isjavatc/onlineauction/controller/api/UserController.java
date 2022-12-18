package com.github.mfnsvrtm.isjavatc.onlineauction.controller.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public String listUsers() {
        return "users";
    }

    @GetMapping("/api/users/{userId}")
    public String getUser(@PathVariable int userId) {
        return "user %d".formatted(userId);
    }

    @PostMapping("/api/users/register")
    public String registerUser() {
        return "registering user";
    }

    @DeleteMapping("/api/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        return "deleting user %d".formatted(userId);
    }

}
