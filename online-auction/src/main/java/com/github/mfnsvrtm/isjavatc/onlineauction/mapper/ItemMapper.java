package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.ItemDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Item;
import org.mapstruct.*;

@Named("ItemMapper")
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, UserMapper.class, LotMapper.class})
public interface ItemMapper extends GenericEntityMapper<Item, ItemDto> {

    @Override
    @Named("toDto")
    @Mapping(target = "category", qualifiedByName = {"CategoryMapper", "toDto"})
    @Mapping(target = "seller", qualifiedByName = {"UserMapper", "toDtoSummary"})
    @Mapping(target = "lot", qualifiedByName = {"LotMapper", "toDtoId"})
    ItemDto toDto(Item entity);

    @Override
    @Named("toEntity")
    @Mapping(target = "category", qualifiedByName = {"CategoryMapper", "toEntity"})
    @Mapping(target = "seller", qualifiedByName = {"UserMapper", "toEntity"})
    @Mapping(target = "lot", qualifiedByName = {"LotMapper", "toEntity"})
    Item toEntity(ItemDto dto);

}
