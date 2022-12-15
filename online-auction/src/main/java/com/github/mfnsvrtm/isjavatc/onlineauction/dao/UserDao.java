package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserDao extends ListCrudRepository<User, Integer> {

}
