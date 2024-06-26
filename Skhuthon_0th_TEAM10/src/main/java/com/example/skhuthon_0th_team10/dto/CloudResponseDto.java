package com.example.skhuthon_0th_team10.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudResponseDto {

    private List<Map<String, Object>> reviewsList;

    public static CloudResponseDto create(List<Map<String, Object>> reviewsList) {
        return CloudResponseDto.builder()
                .reviewsList(reviewsList)
                .build();
    }
}
