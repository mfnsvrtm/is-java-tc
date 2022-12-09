package com.github.mfnsvrtm.isjavatc.task3.dao.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.CategoryDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.Category;

import java.util.List;
import java.util.Optional;

public class CategoryRepository extends CommonRepository<Category> implements CategoryDao {

    @Override
    public List<Category> getAll() {
        return getAll(Category.class);
    }

    @Override
    public Optional<Category> getById(int id) {
        return getById(id, Category.class);
    }

}
