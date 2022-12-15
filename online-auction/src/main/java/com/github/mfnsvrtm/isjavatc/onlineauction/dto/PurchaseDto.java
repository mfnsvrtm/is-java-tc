package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PurchaseDto {

    ItemDto item;
    BigDecimal amount;
    String paymentMethod;

}
