package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Purchase;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;

import java.util.List;

public interface PurchaseDao extends Dao<Purchase> {

    List<Purchase> getByUser(User user);

}
