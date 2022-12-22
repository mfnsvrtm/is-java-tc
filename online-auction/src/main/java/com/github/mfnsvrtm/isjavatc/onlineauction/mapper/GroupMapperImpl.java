package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public String toString(Group group) {
        return group.getName();
    }

    @Override
    public Group toGroup(String group) {
        Group mapped = new Group();
        mapped.setName(group);
        return mapped;
    }

}
