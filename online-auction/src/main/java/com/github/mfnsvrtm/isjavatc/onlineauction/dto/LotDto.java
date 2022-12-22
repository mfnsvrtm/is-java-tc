package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class LotDto {

    Integer id;
    ItemDto item;
    BigDecimal startingPrice;
    BigDecimal minimumBidIncrement;
    OffsetDateTime auctionStart;
    OffsetDateTime auctionEnd;
    BigDecimal currentPrice;
    List<BidDto> topBids;

}
