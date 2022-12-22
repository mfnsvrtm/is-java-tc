package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.UserCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.UserDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/api/users")
    public UserDto createUser(@Valid @RequestBody UserCreationDto userCreationDto) {
        return userService.createUser(userCreationDto);
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }

}
