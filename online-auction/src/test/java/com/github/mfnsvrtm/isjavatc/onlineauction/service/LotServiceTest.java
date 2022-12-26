package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.TestData;
import com.github.mfnsvrtm.isjavatc.onlineauction.dao.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.CategoryDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.ItemDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.LotDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Category;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Lot;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import com.github.mfnsvrtm.isjavatc.onlineauction.exception.AuctionException;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.security.RoleAuthority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LotServiceTest {

    @InjectMocks
    private LotService lotService;
    @Mock
    private LotDao lotDao;
    @Mock
    private BidDao bidDao;
    @Mock
    private UserDao userDao;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private UserDetails userDetails;

    @Spy
    private final LotMapper lotMapper = Mappers.getMapper(LotMapper.class);
    @Spy
    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
    @Spy
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    @Spy
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Spy
    private final BidMapper bidMapper = Mappers.getMapper(BidMapper.class);

    void setUpMappers() {
        // https://stackoverflow.com/a/66327399
        ReflectionTestUtils.setField(itemMapper, "categoryMapper", categoryMapper);
        ReflectionTestUtils.setField(itemMapper, "userMapper", userMapper);
        ReflectionTestUtils.setField(itemMapper, "lotMapper", lotMapper);
        ReflectionTestUtils.setField(lotMapper, "itemMapper", itemMapper);
        ReflectionTestUtils.setField(lotMapper, "bidMapper", bidMapper);
    }

    @Test
    void createLot() {
        setUpMappers();

        User user0 = TestData.USERS.get(0);
        Category category0 = TestData.CATEGORIES.get(0);

        when(userDetails.getUsername()).thenReturn(user0.getUsername());
        when(userDao.findByUsername(user0.getUsername())).thenReturn(Optional.of(user0));
        when(categoryDao.findById(category0.getId())).thenReturn(Optional.of(category0));

        LotDto newLot = new LotDto(
            null,
            BigDecimal.valueOf(10),
            BigDecimal.valueOf(1),
            OffsetDateTime.of(LocalDateTime.of(2022, 12, 25, 0, 0), ZoneOffset.UTC),
            OffsetDateTime.of(LocalDateTime.of(2023, 1, 1, 0, 0), ZoneOffset.UTC),
            new ItemDto(
                null,
                "name",
                "description",
                "condition",
                new CategoryDto(
                    category0.getId(),
                    null
                ),
                null,
                null
            ),
            null,
            null
        );

        lotService.createLot(newLot, userDetails);

        ArgumentCaptor<Lot> captor = ArgumentCaptor.forClass(Lot.class);
        verify(lotDao).save(captor.capture());

        Lot actual = captor.getValue();
        assertEquals(newLot.getStartingPrice(), actual.getCurrentPrice());
        assertEquals(newLot.getMinimumBidIncrement(), actual.getMinimumBidIncrement());
        assertEquals(newLot.getAuctionStart(), actual.getAuctionStart());
        assertEquals(newLot.getAuctionEnd(), actual.getAuctionEnd());
        assertEquals(newLot.getItem().getName(), actual.getItem().getName());
        assertEquals(newLot.getItem().getDescription(), actual.getItem().getDescription());
        assertEquals(newLot.getItem().getCondition(), actual.getItem().getCondition());
        assertEquals(category0, actual.getItem().getCategory());
        assertEquals(user0, actual.getItem().getSeller());
    }

    @Test
    void removeLot_ByAdmin() {
        when(userDetails.getAuthorities()).thenAnswer(i -> List.of(new RoleAuthority("administrator")));

        lotService.removeLot(1, userDetails);

        verify(lotDao).deleteById(1);
    }

    @Test
    void removeLot_ByUser() {
        User user1 = TestData.USERS.get(1);
        Lot lot2 = TestData.LOTS.get(2);

        when(userDetails.getUsername()).thenReturn(user1.getUsername());
        when(userDetails.getAuthorities()).thenReturn(List.of());
        when(lotDao.findById(lot2.getId())).thenReturn(Optional.of(lot2));

        lotService.removeLot(lot2.getId(), userDetails);

        verify(lotDao).deleteById(lot2.getId());
    }

    @Test
    void removeLot_ByUser_ThrowsUnauthorizedException() {
        User user0 = TestData.USERS.get(0);
        Lot lot2 = TestData.LOTS.get(2);

        when(userDetails.getUsername()).thenReturn(user0.getUsername());
        when(userDetails.getAuthorities()).thenReturn(List.of());
        when(lotDao.findById(lot2.getId())).thenReturn(Optional.of(lot2));

        AuctionException actual = assertThrowsExactly(AuctionException.class, () -> lotService.removeLot(lot2.getId(), userDetails));
        assertEquals("Unauthorized attempt to remove a lot.", actual.getMessage());
    }

    @Test
    void removeLot_ByUser_ThrowsActiveBidsException() {
        User user0 = TestData.USERS.get(0);
        Lot lot0 = TestData.LOTS.get(0);

        when(userDetails.getUsername()).thenReturn(user0.getUsername());
        when(userDetails.getAuthorities()).thenReturn(List.of());
        when(lotDao.findById(lot0.getId())).thenReturn(Optional.of(lot0));
        when(bidDao.existsByLotIdAndRetractedFalse(lot0.getId())).thenReturn(!lot0.getBids().isEmpty());

        AuctionException actual = assertThrowsExactly(AuctionException.class, () -> lotService.removeLot(lot0.getId(), userDetails));
        assertEquals("Attempted to remove a lot with active bids.", actual.getMessage());
    }

}
