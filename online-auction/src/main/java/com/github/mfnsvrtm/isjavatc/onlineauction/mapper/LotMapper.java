package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.LotDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Lot;
import org.mapstruct.*;

@Named("LotMapper")
@Mapper(componentModel = "spring", uses = {ItemMapper.class, BidMapper.class})
public interface LotMapper extends GenericEntityMapper<Lot, LotDto> {

    @Override
    @Named("toDto")
    @Mapping(target = "item", qualifiedByName = {"ItemMapper", "toDto"})
    @Mapping(target = "winningBid", qualifiedByName = {"BidMapper", "toDto"})
    @Mapping(target = "bids", ignore = true)
    LotDto toDto(Lot entity);

    @Override
    @Named("toEntity")
    @Mapping(target = "item", qualifiedByName = {"ItemMapper", "toEntity"})
    @Mapping(target = "winningBid", qualifiedByName = {"BidMapper", "toEntity"})
    @Mapping(target = "bids", qualifiedByName = {"BidMapper", "toEntity"})
    Lot toEntity(LotDto dto);

    @Named("toDtoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    LotDto toDtoId(Lot entity);

    @Named("toDtoFull")
    @InheritConfiguration(name = "toDto")
    @Mapping(target = "bids", qualifiedByName = {"BidMapper", "toDto"})
    LotDto toDtoFull(Lot entity);

}
