package com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary;


import com.github.mfnsvrtm.isjavatc.onlineauction.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@Builder
@AllArgsConstructor
public class LotSummaryDto {

    Integer id;
    ItemDto item;
    BigDecimal currentPrice;
    OffsetDateTime auctionEnd;

}
