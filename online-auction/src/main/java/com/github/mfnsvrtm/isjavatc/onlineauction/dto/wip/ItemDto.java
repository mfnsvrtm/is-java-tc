package com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ItemDto {

    Integer id;
    String name;
    String description;
    String condition;

    CategoryDto category;
    UserDto seller;
    LotDto lot;

}
