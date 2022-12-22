package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public String toString(Category category) {
        return category == null ? null : category.getName();
    }

    @Override
    public Category toCategory(String category) {
        Category mapped = new Category();
        mapped.setName(category);
        return mapped;
    }

}
