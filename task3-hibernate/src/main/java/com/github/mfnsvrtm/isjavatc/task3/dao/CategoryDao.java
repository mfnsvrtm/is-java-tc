package com.github.mfnsvrtm.isjavatc.task3.dao;

import com.github.mfnsvrtm.isjavatc.task3.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryDao extends ListCrudRepository<Category, Integer> {

}
