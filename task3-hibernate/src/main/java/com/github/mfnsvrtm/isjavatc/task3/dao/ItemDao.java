package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemDao extends ListCrudRepository<Item, Integer> {

    List<Item> findBySellerId(Integer sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE Item SET description = ?2 WHERE id = ?1")
    void updateDescription(Integer itemId, String newDescription);

    @Transactional
    @Modifying
    @Query("UPDATE Item SET name = ?2 WHERE id = ?1")
    void updateName(Integer itemId, String newName);

}
