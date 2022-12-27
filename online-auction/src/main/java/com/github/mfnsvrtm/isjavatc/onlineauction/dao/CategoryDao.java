package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CategoryDao extends ListCrudRepository<Category, Integer> {

    Optional<Category> findByName(String name);

}
