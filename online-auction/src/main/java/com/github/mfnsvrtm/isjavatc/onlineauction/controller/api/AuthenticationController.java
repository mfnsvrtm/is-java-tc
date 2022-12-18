package com.github.mfnsvrtm.isjavatc.onlineauction.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public String login() {
        return "logging in";
    }

    @PostMapping("/logout")
    public String logout() {
        return "logging out";
    }

}
