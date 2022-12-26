package com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip;

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
    BigDecimal startingPrice;
    BigDecimal minimumBidIncrement;
    OffsetDateTime auctionStart;
    OffsetDateTime auctionEnd;

    ItemDto item;
    BidDto winningBid;
    List<BidDto> bids;
    
}
