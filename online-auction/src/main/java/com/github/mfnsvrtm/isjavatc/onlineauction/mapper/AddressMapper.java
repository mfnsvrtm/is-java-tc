package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.AddressDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Address;
import org.mapstruct.*;

@Named("AddressMapper")
@Mapper(componentModel = "spring")
public interface AddressMapper extends GenericEntityMapper<Address, AddressDto> {

    @Override
    @Named("toDto")
    AddressDto toDto(Address entity);

    @Override
    @Named("toEntity")
    Address toEntity(AddressDto dto);

}
