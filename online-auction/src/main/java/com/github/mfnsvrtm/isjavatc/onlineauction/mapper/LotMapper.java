package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.LotCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.LotDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary.LotSummaryDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Bid;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Lot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, GroupMapper.class, CategoryMapper.class})
public interface LotMapper {

    LotSummaryDto toPreviewDto(Lot lot);

    LotDto toDto(Lot lot, List<Bid> topBids);

    @Mapping(target = "item.name", source = "name")
    @Mapping(target = "item.description", source = "description")
    @Mapping(target = "item.condition", source = "condition")
    @Mapping(target = "item.category", ignore = true)
    @Mapping(target = "item.seller", ignore = true)
    Lot toLot(LotCreationDto lotCreationDto);

}
