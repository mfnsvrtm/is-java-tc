package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@Builder
@AllArgsConstructor
public class BidDto {

    Integer id;
    BigDecimal amount;
    OffsetDateTime time;
    Boolean retracted;

    LotDto lot;
    UserDto bidder;

}
