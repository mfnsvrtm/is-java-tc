package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.GroupDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Group;
import org.mapstruct.*;

@Named("GroupMapper")
@Mapper(componentModel = "spring")
public interface GroupMapper extends GenericEntityMapper<Group, GroupDto> {

    @Override
    @Named("toDto")
    GroupDto toDto(Group entity);

    @Override
    @Named("toEntity")
    Group toEntity(GroupDto dto);

}
