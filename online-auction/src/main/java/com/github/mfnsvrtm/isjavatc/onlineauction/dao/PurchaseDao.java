package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Purchase;
import org.springframework.data.repository.ListCrudRepository;

public interface PurchaseDao extends ListCrudRepository<Purchase, Integer> {

}
