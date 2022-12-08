package com.github.mfnsvrtm.isjavatc.task3.dto;

import com.github.mfnsvrtm.isjavatc.task3.entity.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationDto {

    @NotNull
    @Size(max = 30)
    String userName;
    @NotNull
    @Size(max = 30)
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
    Address address;

}
