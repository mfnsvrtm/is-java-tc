package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.CategoryDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Category;
import org.mapstruct.*;

@Named("CategoryMapper")
@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericEntityMapper<Category, CategoryDto> {

    @Override
    @Named("toDto")
    CategoryDto toDto(Category entity);

    @Override
    @Named("toEntity")
    Category toEntity(CategoryDto dto);

}
