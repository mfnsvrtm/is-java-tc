package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Bid;
import com.github.mfnsvrtm.isjavatc.task3.entity.Lot;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;

import java.util.List;

public interface BidDao extends Dao<Bid> {

    List<Bid> getByUser(User user);

    List<Bid> getByLot(Lot lot);

}
