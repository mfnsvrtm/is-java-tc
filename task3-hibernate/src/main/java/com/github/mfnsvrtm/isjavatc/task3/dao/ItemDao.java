package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Item;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;

import java.util.List;

public interface ItemDao extends Dao<Item> {

    List<Item> getByUser(User user);

    void updateDescription(Item item, String newDescription);

    void updateName(Item item, String newName);

}
