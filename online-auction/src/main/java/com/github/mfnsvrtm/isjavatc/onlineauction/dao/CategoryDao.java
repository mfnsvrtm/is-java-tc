package com.github.mfnsvrtm.isjavatc.onlineauction.dao;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryDao extends ListCrudRepository<Category, Integer> {

}
