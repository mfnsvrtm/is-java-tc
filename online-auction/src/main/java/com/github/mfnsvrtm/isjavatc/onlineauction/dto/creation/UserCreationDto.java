package com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class UserCreationDto {

    @NotNull
    @Size(max = 30)
    String username;
    @NotNull
    @Size(min = 3, max = 30)
    String password;
    @NotNull
    @Size(max = 30)
    String firstName;
    @NotNull
    @Size(max = 30)
    String lastName;
    @NotNull
    @Size(max = 100)
    String email;
    @NotNull
    @Size(max = 30)
    String phoneNumber;
    @NotNull
    String address;

}

