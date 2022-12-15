package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserDao extends ListCrudRepository<User, Integer> {

}
