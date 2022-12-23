package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.dao.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.LotCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary.LotSummaryDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.update.LotUpdateDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.exception.AuctionException;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.LotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LotService {

    private final ItemDao itemDao;
    private final LotDao lotDao;
    private final BidDao bidDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;

    private final LotMapper lotMapper;

    public List<LotSummaryDto> getLots(Integer categoryId, String name) {
        return lotDao.findByItemCategoryIdAndItemName(categoryId, name).stream().map(lotMapper::toPreviewDto).toList();
    }

    public LotDto getLotById(int id) {
        List<Bid> topBids = bidDao.findTopBidsByLotId(id);
        return lotMapper.toDto(lotDao.findById(id).get(), topBids);
    }

    public List<LotSummaryDto> getLotsByCurrentUser(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return lotDao.findByItemSellerUsername(username).stream().map(lotMapper::toPreviewDto).toList();
    }

    public LotDto createLot(LotCreationDto lotCreationDto, UserDetails userDetails) {
        User user = userDao.findByUsername(userDetails.getUsername()).get();

        Category category;
        if (lotCreationDto.getCategoryId() == null) {
            category = null;
        } else {
            category = categoryDao.findById(lotCreationDto.getCategoryId()).get();
        }

        Lot lot = lotMapper.toLot(lotCreationDto);
        lot.getItem().setSeller(user);
        lot.getItem().setCategory(category);

        Lot saved = lotDao.save(lot);
        return lotMapper.toDto(saved, List.of());
    }

    public void removeLot(int lotId, UserDetails userDetails) {
        if (!isAdministrator(userDetails)) {
            Lot lot = lotDao.findById(lotId).get();
            if (!lotBelongsToUser(lot, userDetails)) {
                throw new AuctionException("Unauthorized attempt to remove a lot.");
            }
            if (bidDao.existsByLotIdAndRetractedFalse(lotId)) {
                throw new AuctionException("Attempted to remove a lot with active bids.");
            }
        }

        lotDao.deleteById(lotId);
    }

    @Transactional
    public LotDto updateLot(int lotId, LotUpdateDto lotUpdateDto, UserDetails userDetails) {
        int itemId = itemDao.findByLotId(lotId).get().getId();
        if (lotUpdateDto.getNewName() != null) {
            itemDao.updateName(itemId, lotUpdateDto.getNewName());
        }
        if (lotUpdateDto.getNewDescription() != null) {
            itemDao.updateDescription(itemId, lotUpdateDto.getNewDescription());
        }

        List<Bid> topBids = bidDao.findTopBidsByLotId(lotId);
        return lotMapper.toDto(lotDao.findById(lotId).get(), topBids);
    }

    private boolean isAdministrator(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMINISTRATOR"));

    }

    private boolean lotBelongsToUser(Lot lot, UserDetails userDetails) {
        return lot.getItem().getSeller().getUsername().equals(userDetails.getUsername());
    }

}
