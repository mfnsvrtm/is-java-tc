package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class LotController {

    @GetMapping("/api/lots")
    public String getAllLots(
        @RequestParam(required = false) Integer categoryId,
        @RequestParam(required = false) String name
    ) {
        return "lots";
    }

    @GetMapping("/api/users/me/lots")
    public String getLotsByCurrentUser() {
        return "lots by current user";
    }

    @PostMapping("/api/lots")
    public String createLot() {
        return "creating lot";
    }

    @PatchMapping("/api/lots/{id}")
    public String updateLotById(@PathVariable int id) {
        return "updating lot %d".formatted(id);
    }

    @DeleteMapping("/api/lots/{id}")
    public String deleteLotById(@PathVariable int id, HttpServletRequest request) {
        if (request.isUserInRole("ADMINISTRATOR")) {
            return "deleting lot %d as admin".formatted(id);
        } else {
            return "attempting to delete lot %d as non-admin".formatted(id);
        }
    }

}
