package com.github.mfnsvrtm.isjavatc.task3.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserDto {

    String displayName;
    List<String> groups;

}
