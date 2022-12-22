package com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Value
@Builder
@AllArgsConstructor
public class BidCreationDto {

    @Positive
    BigDecimal amount;

}
