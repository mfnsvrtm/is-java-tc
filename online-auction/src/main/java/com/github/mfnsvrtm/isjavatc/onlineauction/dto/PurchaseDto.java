package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary.ItemSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class PurchaseDto {

    Integer id;
    ItemSummaryDto item;
    BigDecimal amount;
    String paymentMethod;

}
