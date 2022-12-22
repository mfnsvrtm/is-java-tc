package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary.UserSummaryDto;
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
    UserSummaryDto bidder;
    Integer lotId;
    BigDecimal amount;
    OffsetDateTime time;
    Boolean retracted;

}
