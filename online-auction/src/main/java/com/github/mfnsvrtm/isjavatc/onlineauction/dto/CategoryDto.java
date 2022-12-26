package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CategoryDto {

    Integer id;
    String name;

}
