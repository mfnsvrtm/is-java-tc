package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtil;

import java.util.List;
import java.util.Optional;

interface Dao<Entity> {

    List<Entity> getAll();

    Optional<Entity> getById(int id);

    default void create(Entity entity) {
        SessionUtil.executeInTransaction(session -> session.persist(entity));
    }

    default void update(Entity entity) {
        SessionUtil.executeInTransaction(session -> session.merge(entity));
    }

    default void delete(Entity entity) {
        SessionUtil.executeInTransaction(session -> session.remove(entity));
    }

}
