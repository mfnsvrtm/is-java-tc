package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ItemDao extends ListCrudRepository<Item, Integer> {

    Optional<Item> findByLotId(Integer lotId);

    @Transactional
    @Modifying
    @Query("UPDATE Item SET description = ?2 WHERE id = ?1")
    void updateDescription(Integer itemId, String newDescription);

    @Transactional
    @Modifying
    @Query("UPDATE Item SET name = ?2 WHERE id = ?1")
    void updateName(Integer itemId, String newName);

}
