package com.github.mfnsvrtm.isjavatc.task3.dao.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.Dao;
import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

abstract class CommonRepository<Entity> implements Dao<Entity> {

    @Override
    public void create(Entity entity) {
        SessionUtil.executeInTransaction(session -> session.persist(entity));
    }

    @Override
    public void update(Entity entity) {
        SessionUtil.executeInTransaction(session -> session.merge(entity));
    }

    @Override
    public void delete(Entity entity) {
        SessionUtil.executeInTransaction(session -> session.remove(entity));
    }

    protected List<Entity> getAll(Class<Entity> entityClass) {
        return SessionUtil.applyInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = criteriaBuilder.createQuery(entityClass);

            query.from(entityClass);

            return session.createQuery(query).getResultList();
        });
    }

    protected Optional<Entity> getById(int id, Class<Entity> entityClass) {
        return SessionUtil.applyInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = criteriaBuilder.createQuery(entityClass);

            Root<Entity> entity = query.from(entityClass);
            query.where(criteriaBuilder.equal(entity.get("id"), id));

            return session.createQuery(query).getResultStream().findFirst();
        });
    }
}
