package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Lot;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LotDao extends ListCrudRepository<Lot, Integer> {

    List<Lot> findByItemSellerUsername(String username);

    @Query("""
            FROM Lot WHERE
                (?1 IS NULL OR item.category.id = ?1)
                AND (?2 IS NULL OR UPPER(item.name) LIKE CONCAT('%', UPPER(?2), '%'))
            """)
    List<Lot> findByItemCategoryIdAndItemName(Integer categoryId, String name);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE lot
            SET winning_bid_id = (
                SELECT id FROM bid WHERE lot_id = ?1 AND retracted = FALSE
                ORDER BY amount DESC LIMIT 1
            )""", nativeQuery = true)
    void updateWinningBidForId(Integer lotId);

}
