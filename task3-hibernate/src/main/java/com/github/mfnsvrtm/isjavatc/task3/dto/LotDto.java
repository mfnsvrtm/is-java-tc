package com.github.mfnsvrtm.isjavatc.task3.dto;


import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class LotDto {

    ItemDto item;
    BigDecimal currentPrice;
}