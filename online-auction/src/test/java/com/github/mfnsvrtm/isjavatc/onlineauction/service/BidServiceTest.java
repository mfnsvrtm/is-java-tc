package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.TestData;
import com.github.mfnsvrtm.isjavatc.onlineauction.dao.BidDao;
import com.github.mfnsvrtm.isjavatc.onlineauction.dao.LotDao;
import com.github.mfnsvrtm.isjavatc.onlineauction.dao.UserDao;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.BidCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Bid;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Lot;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import com.github.mfnsvrtm.isjavatc.onlineauction.exception.AuctionException;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.BidMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BidServiceTest {

    @InjectMocks
    private BidService bidService;
    @Mock
    private LotDao lotDao;
    @Mock
    private BidDao bidDao;
    @Mock
    private UserDao userDao;
    @Mock
    private UserDetails userDetails;

    @Spy
    private final BidMapper bidMapper = Mappers.getMapper(BidMapper.class);

    @Test
    void placeBid() {
        Lot lot1 = TestData.LOTS.get(1);
        User user0 = TestData.USERS.get(0);

        when(userDetails.getUsername()).thenReturn(user0.getUsername());
        when(lotDao.findById(lot1.getId())).thenReturn(Optional.of(lot1));
        when(userDao.findByUsername(user0.getUsername())).thenReturn(Optional.of(user0));

        BidCreationDto newBid = new BidCreationDto(BigDecimal.valueOf(115));

        assertDoesNotThrow(() -> bidService.placeBid(lot1.getId(), newBid, userDetails));

        ArgumentCaptor<Bid> bidCaptor = ArgumentCaptor.forClass(Bid.class);
        verify(bidDao).save(bidCaptor.capture());
        Bid actualBid = bidCaptor.getValue();

        ArgumentCaptor<Lot> lotCaptor = ArgumentCaptor.forClass(Lot.class);
        verify(lotDao).save(lotCaptor.capture());
        Lot actualLot = lotCaptor.getValue();

        assertEquals(newBid.getAmount(), actualBid.getAmount());
        assertEquals(false, actualBid.getRetracted());
        assertEquals(user0, actualBid.getBidder());
        assertEquals(lot1, actualBid.getLot());

        assertEquals(lot1.getId(), actualLot.getId());
    }

    @Test
    void placeBid_ThrowsBidAmountInsufficientException() {
        Lot lot1 = TestData.LOTS.get(1);

        when(lotDao.findById(lot1.getId())).thenReturn(Optional.of(lot1));

        BidCreationDto newBid = new BidCreationDto(BigDecimal.valueOf(105));

        AuctionException exception = assertThrowsExactly(AuctionException.class, () -> bidService.placeBid(lot1.getId(), newBid, userDetails));
        assertEquals("Bid placement was cancelled. Bid amount does not cover lot's minimum bid increment.", exception.getMessage());
    }

    @Test
    void retractBid() {
        User user2 = TestData.USERS.get(2);
        Bid bid0 = TestData.BIDS.get(0);

        when(userDetails.getUsername()).thenReturn(user2.getUsername());
        when(bidDao.findById(bid0.getId())).thenReturn(Optional.of(bid0));

        bidService.retractBid(bid0.getId(), userDetails);

        verify(bidDao).retractById(bid0.getId());
        verify(lotDao).updateWinningBidForId(anyInt());
    }

    @Test
    void retractBid_ThrowsUnauthorizedAttemptException() {
        User user0 = TestData.USERS.get(0);
        Bid bid0 = TestData.BIDS.get(0);

        when(userDetails.getUsername()).thenReturn(user0.getUsername());
        when(bidDao.findById(bid0.getId())).thenReturn(Optional.of(bid0));

        AuctionException exception = assertThrowsExactly(AuctionException.class, () -> bidService.retractBid(bid0.getId(), userDetails));
        assertEquals("Unauthorized attempt to retract a bid.", exception.getMessage());
    }

}
