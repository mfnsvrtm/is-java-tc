package com.github.mfnsvrtm.isjavatc.onlineauction.dto.update;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class LotUpdateDto {

    @Size(max = 50)
    String newName;
    String newDescription;

}
