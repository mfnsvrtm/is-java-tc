package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.UserDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import org.mapstruct.*;

@Named("UserMapper")
@Mapper(componentModel = "spring", uses = {AddressMapper.class, GroupMapper.class, ItemMapper.class, BidMapper.class})
public interface UserMapper extends GenericEntityMapper<User, UserDto> {

    @Override
    @Named("toDto")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "address", qualifiedByName = {"AddressMapper", "toDto"})
    @Mapping(target = "groups", qualifiedByName = {"GroupMapper", "toDto"})
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "bids", ignore = true)
    UserDto toDto(User entity);

    @Override
    @Named("toEntity")
    User toEntity(UserDto userDto);

    @Named("toDtoSummary")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    UserDto toDtoSummary(User entity);

    @Named("toDtoFull")
    @InheritConfiguration(name = "toDto")
    @Mapping(target = "items", qualifiedByName = {"ItemMapper", "toDto"})
    @Mapping(target = "bids", qualifiedByName = {"BidMapper", "toDto"})
    UserDto toDtoFull(User entity);

}
