package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.dao.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.BidCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.exception.AuctionException;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.BidMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {

    private final LotDao lotDao;
    private final BidDao bidDao;
    private final UserDao userDao;

    private final BidMapper bidMapper;

    public BidDto getBidById(int id) {
        return bidMapper.toDto(bidDao.findById(id).get());
    }

    public List<BidDto> getBidsByLotId(int id) {
        return bidDao.findByLotId(id).stream().map(bidMapper::toDto).toList();
    }

    public List<BidDto> getBidsByUserId(int id) {
        return bidDao.findByBidderId(id).stream().map(bidMapper::toDto).toList();
    }

    public List<BidDto> getBidsByCurrentUser(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return bidDao.findByBidderUsername(username).stream().map(bidMapper::toDto).toList();
    }

    @Transactional
    public BidDto placeBid(int lotId, BidCreationDto bidCreationDto, UserDetails userDetails) {
        Lot lot = lotDao.findById(lotId).get();
        BigDecimal amount = bidCreationDto.getAmount();

        if (!isBidAmountSufficient(amount, lot)) {
            throw new AuctionException("Bid placement was cancelled. Bid amount does not cover lot's minimum bid increment.");
        }

        User user = userDao.findByUsername(userDetails.getUsername()).get();
        Bid saved = bidDao.save(new Bid(amount, lot, user));

        lot.setWinningBid(saved);
        lotDao.save(lot);

        return bidMapper.toDto(saved);
    }

    @Transactional
    public void retractBid(int bidId, UserDetails userDetails) {
        Bid bid = bidDao.findById(bidId).get();
        if (!bidBelongsToUser(bid, userDetails)) {
            throw new AuctionException("Unauthorized attempt to retract a bid.");
        }

        bidDao.retractById(bidId);
        int lotId = bid.getLot().getId();
        lotDao.updateWinningBidForId(lotId);
    }

    private boolean isBidAmountSufficient(BigDecimal amount, Lot lot) {
        BigDecimal minimumAllowedBid = lot.getCurrentPrice().add(lot.getMinimumBidIncrement());
        return amount.compareTo(minimumAllowedBid) >= 0;
    }

    private boolean bidBelongsToUser(Bid bid, UserDetails userDetails) {
        return bid.getBidder().getUsername().equals(userDetails.getUsername());
    }

}
