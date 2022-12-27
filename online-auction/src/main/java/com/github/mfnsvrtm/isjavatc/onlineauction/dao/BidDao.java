package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Bid;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BidDao extends ListCrudRepository<Bid, Integer> {

    List<Bid> findByBidderId(Integer bidderId);

    List<Bid> findByBidderUsername(String username);

    List<Bid> findByLotId(Integer lotId);

    List<Bid> findTop20ByLotIdAndRetractedFalseOrderByAmountDescTimeDesc(Integer lotId);

    boolean existsByLotIdAndRetractedFalse(Integer lotId);

    @Transactional
    @Modifying
    @Query("UPDATE Bid SET retracted = TRUE WHERE id = ?1")
    void retractById(Integer id);

}
