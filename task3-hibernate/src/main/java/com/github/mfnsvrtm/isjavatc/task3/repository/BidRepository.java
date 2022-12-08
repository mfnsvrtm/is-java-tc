package com.github.mfnsvrtm.isjavatc.task3.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.BidDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.Bid;
import com.github.mfnsvrtm.isjavatc.task3.entity.Lot;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;
import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtil;

import java.util.List;
import java.util.Optional;

public class BidRepository extends Repository<Bid> implements BidDao {

    @Override
    public List<Bid> getAll() {
        return getAll(Bid.class);
    }

    @Override
    public Optional<Bid> getById(int id) {
        return getById(id, Bid.class);
    }

    @Override
    public List<Bid> getByUser(User user) {
        return SessionUtil.applyInTransaction(session ->
                session.createQuery("FROM Bid WHERE bidder.id = :id", Bid.class)
                        .setParameter("id", user.getId())
                        .getResultList()
        );
    }

    @Override
    public List<Bid> getByLot(Lot lot) {
        return SessionUtil.applyInTransaction(session ->
                session.createQuery("FROM Bid WHERE lot.id = :id", Bid.class)
                        .setParameter("id", lot.getId())
                        .getResultList()
        );
    }
}
