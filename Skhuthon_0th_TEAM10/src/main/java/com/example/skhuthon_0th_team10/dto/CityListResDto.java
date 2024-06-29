package com.example.skhuthon_0th_team10.dto;

import com.example.skhuthon_0th_team10.domain.City;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CityListResDto {
    private Long cityId;
    private String name;
    private String cityImage;
    private String discription;

    public static CityListResDto from (City city) {
        return CityListResDto.builder()
                .cityId(city.getId())
                .name(city.getName())
                .cityImage(city.getCityImage())
                .discription(city.getDiscription())
                .build();
    }
}
