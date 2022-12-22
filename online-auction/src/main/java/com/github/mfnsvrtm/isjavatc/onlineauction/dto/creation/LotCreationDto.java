package com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@Builder
@AllArgsConstructor
public class LotCreationDto {

    @NotNull
    @Positive
    BigDecimal startingPrice;
    @NotNull
    @Positive
    BigDecimal minimumBidIncrement;
    @NotNull
    @FutureOrPresent
    OffsetDateTime auctionStart;
    @NotNull
    @FutureOrPresent
    OffsetDateTime auctionEnd;

    @NotNull
    @Size(max = 50)
    String name;
    String description;
    @NotNull
    @Size(max = 50)
    String condition;
    Integer categoryId;

}
