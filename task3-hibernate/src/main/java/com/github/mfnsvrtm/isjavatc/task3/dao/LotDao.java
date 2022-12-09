package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Category;
import com.github.mfnsvrtm.isjavatc.task3.entity.Lot;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;

import java.util.List;

public interface LotDao extends Dao<Lot> {

    List<Lot> getByUser(User user);

    List<Lot> getByCategory(Category category);

}
