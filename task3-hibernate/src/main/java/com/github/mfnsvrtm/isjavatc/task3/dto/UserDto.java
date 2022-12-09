package com.github.mfnsvrtm.isjavatc.task3.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserDto {

    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;
    List<String> groups;

}
