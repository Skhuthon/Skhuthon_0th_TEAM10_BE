package com.example.skhuthon_0th_team10.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CityListResDto {
    private Long cityId;
    private String name;
    private String cityImage;
}
