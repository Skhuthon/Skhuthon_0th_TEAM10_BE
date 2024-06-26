package com.example.skhuthon_0th_team10.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewSaveReqDto(
        @NotBlank(message = "명소 이름은 필수로 입력해야 합니다.")
        String placeName1,

        @NotBlank(message = "명소 설명은 필수로 입력해야 합니다.")
        String placeInfo1,
        String placeImage1
) {
}
