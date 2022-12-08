package com.github.mfnsvrtm.isjavatc.task3.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.GroupDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.Group;

import java.util.List;
import java.util.Optional;

public class GroupRepository extends Repository<Group> implements GroupDao {

    @Override
    public List<Group> getAll() {
        return getAll(Group.class);
    }

    @Override
    public Optional<Group> getById(int id) {
        return getById(id, Group.class);
    }

}