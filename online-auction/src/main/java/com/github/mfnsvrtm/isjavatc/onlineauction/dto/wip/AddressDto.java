package com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class AddressDto {

    Integer id;
    String address;

}
