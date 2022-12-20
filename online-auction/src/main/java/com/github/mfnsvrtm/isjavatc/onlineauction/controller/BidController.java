package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BidController {

    @PostMapping("/api/lots/{lotId}/bids")
    public String placeBid(@PathVariable int lotId) {
        return "placing bid on lot %d".formatted(lotId);
    }

    @DeleteMapping("/api/bids/{bidId}")
    public String retractBid(@PathVariable int bidId) {
        return "retracting bid %d".formatted(bidId);
    }

}
