package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary.UserSummaryDto;
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
    String category;
    UserSummaryDto seller;

}
