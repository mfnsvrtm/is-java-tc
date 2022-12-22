package com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ItemSummaryDto {

    Integer id;
    String name;
    UserSummaryDto seller;

}
