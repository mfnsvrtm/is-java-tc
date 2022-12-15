package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Purchase;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PurchaseDao extends ListCrudRepository<Purchase, Integer> {

    List<Purchase> findByBidBidderId(Integer bidderId);

}
