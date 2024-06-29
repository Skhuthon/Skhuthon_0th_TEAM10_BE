package com.example.skhuthon_0th_team10.dto;

import com.example.skhuthon_0th_team10.domain.City;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CityResDto {
    private String name;
    private String info;
    private String recommendation;
    private String cityImage;

    public static CityResDto from(City city) {
        return CityResDto.builder()
                .name(city.getName())
                .info(city.getInfo())
                .recommendation(city.getRecommendation())
                .cityImage(city.getCityImage())
                .build();
    }
}
