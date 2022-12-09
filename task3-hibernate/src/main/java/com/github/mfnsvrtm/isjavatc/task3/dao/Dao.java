package com.github.mfnsvrtm.isjavatc.task3.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<Entity> {

    List<Entity> getAll();

    Optional<Entity> getById(int id);

    void create(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);

}
