package com.example.skhuthon_0th_team10.dto;

import com.example.skhuthon_0th_team10.domain.City;
import com.example.skhuthon_0th_team10.domain.Review;
import com.example.skhuthon_0th_team10.domain.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ReviewSaveReqDto(
        //Long userId,

        @NotBlank(message = "명소 이름은 필수로 입력해야 합니다.")
        String placeName1,

        @NotBlank(message = "명소 설명은 필수로 입력해야 합니다.")
        String placeInfo1

) {
        public Review toEntity(User user, City city, String image) {
                return Review.builder()
                        .placeName1(placeName1)
                        .placeInfo1(placeInfo1)
                        .placeImage1(image)
                        .user(user)
                        .city(city)
                        .build();
        }
}
