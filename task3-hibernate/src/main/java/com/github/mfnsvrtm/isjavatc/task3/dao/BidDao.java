package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Bid;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BidDao extends ListCrudRepository<Bid, Integer> {

    List<Bid> findByBidderId(Integer bidderId);

    List<Bid> findByLotId(Integer lotId);

}
