package com.github.mfnsvrtm.isjavatc.task3.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.UserDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;

import java.util.List;
import java.util.Optional;

public class UserRepository extends Repository<User> implements UserDao {

    @Override
    public List<User> getAll() {
        return getAll(User.class);
    }

    @Override
    public Optional<User> getById(int id) {
        return getById(id, User.class);
    }

}
