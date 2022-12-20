package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BidController {

    @PostMapping("/api/lots/{id}/bids")
    public String createBidForLotId(@PathVariable int id) {
        return "creating bid for lot %d".formatted(id);
    }

    @DeleteMapping("/api/bids/{id}")
    public String deleteBidById(@PathVariable int id) {
        return "deleting bid %d".formatted(id);
    }

}
