package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Address;

public interface AddressMapper {

    String toString(Address address);

    Address toAddress(String address);

}
