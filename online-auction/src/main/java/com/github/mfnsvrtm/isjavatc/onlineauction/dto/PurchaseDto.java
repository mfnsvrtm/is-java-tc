package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class PurchaseDto {

    Integer id;
    BigDecimal amount;
    String paymentMethod;
    String bankAccountNumber;

    BidDto bid;
    
}
