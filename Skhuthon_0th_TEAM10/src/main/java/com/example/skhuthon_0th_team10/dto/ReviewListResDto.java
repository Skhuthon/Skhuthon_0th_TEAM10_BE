package com.example.skhuthon_0th_team10.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewListResDto(
        List<ReviewInfoResDto> reviews
) {
    public static ReviewListResDto from(List<ReviewInfoResDto> reviews) {
        return ReviewListResDto.builder()
                .reviews(reviews)
                .build();
    }
}
