package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.UserDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/api/users/me")
    public UserDto getUserById(Authentication authentication) {
        return userService.getCurrentUser((UserDetails) authentication.getPrincipal());
    }

    @PostMapping("/api/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PatchMapping("/api/users/me")
    public UserDto updateLotById(@RequestBody UserDto userDto, Authentication authentication) {
        return userService.updateUser(userDto, (UserDetails) authentication.getPrincipal());
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }

}
