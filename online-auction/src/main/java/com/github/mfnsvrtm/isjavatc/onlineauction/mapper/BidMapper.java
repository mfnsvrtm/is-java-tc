package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.BidDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Bid;
import org.mapstruct.*;

@Named("BidMapper")
@Mapper(componentModel = "spring", uses = {LotMapper.class, UserMapper.class})
public interface BidMapper extends GenericEntityMapper<Bid, BidDto> {

    @Override
    @Named("toDto")
    @Mapping(target = "lot", qualifiedByName = {"LotMapper", "toDtoId"})
    @Mapping(target = "bidder", qualifiedByName = {"UserMapper", "toDtoSummary"})
    BidDto toDto(Bid entity);

    @Override
    @Named("toEntity")
    @Mapping(target = "lot", qualifiedByName = {"LotMapper", "toEntity"})
    @Mapping(target = "bidder", qualifiedByName = {"UserMapper", "toEntity"})
    Bid toEntity(BidDto dto);

}
