package com.example.skhuthon_0th_team10.dto;

import com.example.skhuthon_0th_team10.domain.Country;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountryListResDto {
    private Long countryId;
    private String name;
    private String countryImage;

    public static CountryListResDto from(Country country) {
        return  CountryListResDto.builder()
                .countryId(country.getId())
                .name(country.getName())
                .countryImage(country.getCountryImage())
                .build();
    }
}
