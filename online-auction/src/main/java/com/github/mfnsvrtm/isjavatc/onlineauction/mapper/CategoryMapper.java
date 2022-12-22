package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Category;

public interface CategoryMapper {

    String toString(Category category);

    Category toCategory(String category);

}
