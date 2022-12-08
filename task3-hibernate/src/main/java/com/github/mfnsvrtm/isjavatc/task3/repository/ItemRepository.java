package com.github.mfnsvrtm.isjavatc.task3.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.ItemDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.Item;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;
import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtil;

import java.util.List;
import java.util.Optional;

public class ItemRepository extends Repository<Item> implements ItemDao {

    @Override
    public List<Item> getAll() {
        return getAll(Item.class);
    }

    @Override
    public Optional<Item> getById(int id) {
        return getById(id, Item.class);
    }

    @Override
    public List<Item> getByUser(User user) {
        return SessionUtil.applyInTransaction(session ->
                session.createQuery("FROM Item WHERE seller.id = :id", Item.class)
                        .setParameter("id", user.getId())
                        .getResultList()
        );
    }

    @Override
    public void updateDescription(Item item, String newDescription) {
        SessionUtil.executeInTransaction(session ->
                session.createQuery("UPDATE Item SET description = :description WHERE id = :id")
                        .setParameter("description", newDescription)
                        .setParameter("id", item.getId())
                        .executeUpdate()
        );
    }

    @Override
    public void updateName(Item item, String newName) {
        SessionUtil.executeInTransaction(session ->
                session.createQuery("UPDATE Item SET name = :name WHERE id = :id")
                        .setParameter("name", newName)
                        .setParameter("id", item.getId())
                        .executeUpdate()
        );
    }
}
