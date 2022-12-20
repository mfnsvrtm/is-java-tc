package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BidController {

    @GetMapping("/api/bids/{id}")
    public String getBidById(@PathVariable int id) {
        return "bid %d".formatted(id);
    }

    @GetMapping("/api/lots/{id}/bids")
    public String getBidsByLotId(@PathVariable int id) {
        return "bids for lot %d".formatted(id);
    }

    @GetMapping("/api/users/{id}/bids")
    public String getBidsByUserId(@PathVariable int id) {
        return "bids for user %d".formatted(id);
    }

    @GetMapping("/api/users/me/bids")
    public String getBidsByCurrentUser() {
        return "bids by current user";
    }

    @PostMapping("/api/lots/{id}/bids")
    public String createBidForLotId(@PathVariable int id) {
        return "creating bid for lot %d".formatted(id);
    }

    @DeleteMapping("/api/bids/{id}")
    public String deleteBidById(@PathVariable int id) {
        return "deleting bid %d".formatted(id);
    }

}
