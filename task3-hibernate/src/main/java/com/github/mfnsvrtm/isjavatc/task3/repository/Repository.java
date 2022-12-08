package com.github.mfnsvrtm.isjavatc.task3.repository;

import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

abstract class Repository<Entity> {
    protected List<Entity> getAll(Class<Entity> entityClass) {
        return SessionUtils.applyInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = criteriaBuilder.createQuery(entityClass);

            query.from(entityClass);

            return session.createQuery(query).getResultList();
        });
    }

    protected Optional<Entity> getById(int id, Class<Entity> entityClass) {
        return SessionUtils.applyInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = criteriaBuilder.createQuery(entityClass);

            Root<Entity> entity = query.from(entityClass);
            query.where(criteriaBuilder.equal(entity.get("id"), id));

            return session.createQuery(query).getResultStream().findFirst();
        });
    }
}
