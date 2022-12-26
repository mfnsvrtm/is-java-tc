package com.github.mfnsvrtm.isjavatc.onlineauction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class UserDto {
    
    Integer id;
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    
    AddressDto address;
    List<GroupDto> groups;
    List<ItemDto> items;
    List<BidDto> bids;

}
