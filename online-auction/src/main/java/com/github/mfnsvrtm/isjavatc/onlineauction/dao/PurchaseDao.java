package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Purchase;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PurchaseDao extends ListCrudRepository<Purchase, Integer> {

    List<Purchase> findByBidBidderId(Integer bidderId);

}
