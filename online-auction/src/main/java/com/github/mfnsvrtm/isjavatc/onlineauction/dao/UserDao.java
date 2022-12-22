package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserDao extends ListCrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
