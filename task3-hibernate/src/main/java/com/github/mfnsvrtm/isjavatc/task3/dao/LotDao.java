package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Lot;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface LotDao extends ListCrudRepository<Lot, Integer> {

    List<Lot> findByItemSellerId(Integer sellerId);

    List<Lot> findByItemCategoryId(Integer categoryId);

}
