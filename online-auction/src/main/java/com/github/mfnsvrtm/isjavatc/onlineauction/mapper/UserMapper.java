package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.UserCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.UserDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary.UserSummaryDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, GroupMapper.class})
public interface UserMapper {

    User toUser(UserCreationDto userCreationDto);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    UserDto toDto(User user);

    UserSummaryDto toSummaryDto(User user);

}
