package com.example.skhuthon_0th_team10.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ReviewSaveReqDto(
        //Long userId,

        @NotBlank(message = "명소 이름은 필수로 입력해야 합니다.")
        String placeName1,

        @NotBlank(message = "명소 설명은 필수로 입력해야 합니다.")
        String placeInfo1

) {
}
