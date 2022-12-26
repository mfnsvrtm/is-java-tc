package com.github.mfnsvrtm.isjavatc.onlineauction.mapper;

public interface GenericEntityMapper<Entity, Dto> {

    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);

}
