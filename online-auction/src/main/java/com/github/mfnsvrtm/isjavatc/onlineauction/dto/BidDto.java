package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@Builder
public class BidDto {

    UserDto user;
    BigDecimal amount;
    OffsetDateTime time;
    Boolean retracted;

}
