package com.github.mfnsvrtm.isjavatc.task3.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.LotDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.Category;
import com.github.mfnsvrtm.isjavatc.task3.entity.Lot;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;
import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class LotRepository extends Repository<Lot> implements LotDao {

    @Override
    public List<Lot> getAll() {
        return getAll(Lot.class);
    }

    @Override
    public Optional<Lot> getById(int id) {
        return SessionUtil.applyInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Lot> query = criteriaBuilder.createQuery(Lot.class);

            Root<Lot> entity = query.from(Lot.class);
            query.where(criteriaBuilder.equal(entity.get("id"), id));

            return session.createQuery(query).getResultStream().findFirst();
        });
    }

    @Override
    public List<Lot> getByUser(User user) {
        return SessionUtil.applyInTransaction(session ->
                session.createQuery("FROM Lot WHERE item.seller.id = :id", Lot.class)
                        .setParameter("id", user.getId())
                        .getResultList()
        );
    }

    @Override
    public List<Lot> getByCategory(Category category) {
        return SessionUtil.applyInTransaction(session ->
                session.createQuery("FROM Lot WHERE item.category.id = :id", Lot.class)
                        .setParameter("id", category.getId())
                        .getResultList()
        );
    }
}
