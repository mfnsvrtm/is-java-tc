package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.BidDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @GetMapping("/api/bids/{id}")
    public BidDto getBidById(@PathVariable int id) {
        return bidService.getBidById(id);
    }

    @GetMapping("/api/lots/{id}/bids")
    public List<BidDto> getBidsByLotId(@PathVariable int id) {
        return bidService.getBidsByLotId(id);
    }

    @GetMapping("/api/users/{id}/bids")
    public List<BidDto> getBidsByUserId(@PathVariable int id) {
        return bidService.getBidsByUserId(id);
    }

    @GetMapping("/api/users/me/bids")
    public List<BidDto> getBidsByCurrentUser(Authentication authentication) {
        return bidService.getBidsByCurrentUser((UserDetails) authentication.getPrincipal());
    }

    @PostMapping("/api/lots/{id}/bids")
    public BidDto createBidForLotId(@PathVariable int id, @RequestBody BidDto bidDto, Authentication authentication) {
        return bidService.placeBid(id, bidDto, (UserDetails) authentication.getPrincipal());
    }

    @DeleteMapping("/api/bids/{id}")
    public void deleteBidById(@PathVariable int id, Authentication authentication) {
        bidService.retractBid(id, (UserDetails) authentication.getPrincipal());
    }

}
