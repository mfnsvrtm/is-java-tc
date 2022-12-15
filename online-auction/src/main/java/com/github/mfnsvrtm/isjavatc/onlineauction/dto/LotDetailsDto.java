package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Value
@Builder
public class LotDetailsDto {

    ItemDto item;
    BigDecimal startingPrice;
    BigDecimal minimumBidIncrement;
    OffsetDateTime auctionStart;
    OffsetDateTime auctionEnd;
    BigDecimal currentPrice;
    List<BidDto> latestBids;

}
