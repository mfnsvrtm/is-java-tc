package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.BidDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface BidMapper {

    @Mapping(target = "lotId", source = "lot.id")
    BidDto toDto(Bid bid);

}
