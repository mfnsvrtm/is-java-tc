package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Bid;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BidDao extends ListCrudRepository<Bid, Integer> {

    List<Bid> findByBidderId(Integer bidderId);

    List<Bid> findByLotId(Integer lotId);

}
