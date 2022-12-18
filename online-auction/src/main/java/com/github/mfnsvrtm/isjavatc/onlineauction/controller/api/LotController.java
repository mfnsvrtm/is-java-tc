package com.github.mfnsvrtm.isjavatc.onlineauction.controller.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class LotController {

    @GetMapping("/api/lots")
    public String listLots(
        @RequestParam(required = false) Integer categoryId,
        @RequestParam(required = false) String name
    ) {
        return "lots";
    }

    @GetMapping("/api/users/me/lots")
    public String listLotsByCurrentUser() {
        return "lots by current user";
    }

    @PostMapping("/api/lots")
    public String createLot() {
        return "creating lot";
    }

    @PatchMapping("/api/lots/{lotId}")
    public String updateLot(@PathVariable int lotId) {
        return "updating lot %d".formatted(lotId);
    }

    @DeleteMapping("/api/lots/{lotId}")
    public String deleteLot(@PathVariable int lotId) {
        return "deleting lot %d".formatted(lotId);
    }

}
