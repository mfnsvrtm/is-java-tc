package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.util.SessionUtils;

import java.util.List;
import java.util.Optional;

interface Dao<Entity> {

    List<Entity> getAll();

    Optional<Entity> getById(int id);

    default void create(Entity entity) {
        SessionUtils.executeInTransaction(session -> session.persist(entity));
    }

    default void update(Entity entity) {
        SessionUtils.executeInTransaction(session -> session.merge(entity));
    }

    default void delete(Entity entity) {
        SessionUtils.executeInTransaction(session -> session.remove(entity));
    }

}
