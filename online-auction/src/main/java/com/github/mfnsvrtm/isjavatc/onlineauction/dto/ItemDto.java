package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ItemDto {

    String name;
    String description;
    String condition;
    String category;
    UserDto seller;

}
