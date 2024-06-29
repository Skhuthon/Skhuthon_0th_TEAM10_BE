package com.example.skhuthon_0th_team10.dto;

import com.example.skhuthon_0th_team10.domain.Review;

import com.example.skhuthon_0th_team10.domain.User;
import lombok.Builder;

@Builder
public record ReviewInfoResDto(
        Long reviewId,
        String placeName1,
        String placeInfo1,
        String placeImage1,
        String userName,
        String pictureUrl

) {
    public static ReviewInfoResDto from(Review review) {
        User user = review.getUser();

        return ReviewInfoResDto.builder()
                .reviewId(review.getId())
                .placeName1(review.getPlaceName1())
                .placeInfo1(review.getPlaceInfo1())
                .placeImage1(review.getPlaceImage1())
                .userName(user.getName())
                .pictureUrl(user.getPictureUrl())
                .build();

    }
}
