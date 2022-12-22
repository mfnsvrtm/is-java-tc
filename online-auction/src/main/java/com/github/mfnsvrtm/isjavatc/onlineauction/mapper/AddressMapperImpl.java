package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public String toString(Address address) {
        return address.getAddress();
    }

    @Override
    public Address toAddress(String address) {
        Address mapped = new Address();
        mapped.setAddress(address);
        return mapped;
    }
}
