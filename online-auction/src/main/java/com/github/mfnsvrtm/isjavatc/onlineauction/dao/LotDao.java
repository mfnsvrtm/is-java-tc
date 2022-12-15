package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Lot;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface LotDao extends ListCrudRepository<Lot, Integer> {

    List<Lot> findByItemSellerId(Integer sellerId);

    List<Lot> findByItemCategoryId(Integer categoryId);

}
