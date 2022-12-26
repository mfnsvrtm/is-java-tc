package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.dao.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.ItemDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.LotDto;
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

    public List<LotDto> getLots(Integer categoryId, String name) {
        return lotDao.findByItemCategoryIdAndItemName(categoryId, name).stream().map(lotMapper::toDto).toList();
    }

    public LotDto getLotById(int id) {
        return lotMapper.toDtoFull(lotDao.findById(id).get());
    }

    public List<LotDto> getLotsByCurrentUser(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return lotDao.findByItemSellerUsername(username).stream().map(lotMapper::toDto).toList();
    }

    public LotDto createLot(LotDto lotDto, UserDetails userDetails) {
        User user = userDao.findByUsername(userDetails.getUsername()).get();

        Category category = null;
        if (lotDto.getItem() != null && lotDto.getItem().getCategory() != null && lotDto.getItem().getCategory().getId() != null) {
            category = categoryDao.findById(lotDto.getItem().getCategory().getId()).get();
        }

        Lot lot = lotMapper.toEntity(lotDto);
        lot.getItem().setSeller(user);
        lot.getItem().setCategory(category);

        Lot saved = lotDao.save(lot);
        return lotMapper.toDto(saved);
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
    public LotDto updateLot(int lotId, LotDto lotDto, UserDetails userDetails) {
        int itemId = itemDao.findByLotId(lotId).get().getId();

        if (lotDto.getItem() != null) {
            ItemDto item = lotDto.getItem();
            if (item.getName() != null) {
                itemDao.updateName(itemId, item.getName());
            }
            if (item.getDescription() != null) {
                itemDao.updateDescription(itemId, item.getDescription());
            }
        }

        return lotMapper.toDto(lotDao.findById(lotId).get());
    }

    private boolean isAdministrator(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMINISTRATOR"));

    }

    private boolean lotBelongsToUser(Lot lot, UserDetails userDetails) {
        return lot.getItem().getSeller().getUsername().equals(userDetails.getUsername());
    }

}
