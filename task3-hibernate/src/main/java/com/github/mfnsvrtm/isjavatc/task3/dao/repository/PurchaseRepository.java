package com.github.mfnsvrtm.isjavatc.task3.dao.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.PurchaseDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.Purchase;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;
import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtil;

import java.util.List;
import java.util.Optional;

public class PurchaseRepository extends Repository<Purchase> implements PurchaseDao {

    @Override
    public List<Purchase> getAll() {
        return getAll(Purchase.class);
    }

    @Override
    public Optional<Purchase> getById(int id) {
        return getById(id, Purchase.class);
    }

    @Override
    public List<Purchase> getByUser(User user) {
        return SessionUtil.applyInTransaction(session ->
                session.createQuery("FROM Purchase WHERE bid.bidder.id = :id", Purchase.class)
                        .setParameter("id", user.getId())
                        .getResultList()
        );
    }
}
