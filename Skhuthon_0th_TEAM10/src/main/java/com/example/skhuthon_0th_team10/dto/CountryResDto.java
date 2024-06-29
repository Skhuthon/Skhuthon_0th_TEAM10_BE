package com.example.skhuthon_0th_team10.dto;

import com.example.skhuthon_0th_team10.domain.Country;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountryResDto {
    private String name;
    private String character;
    private String transportation;
    private String number;
    private String accident;
    private String culture;
    private String countryImage;

    public static CountryResDto from (Country country) {
        return CountryResDto.builder()
                .name(country.getName())
                .character(country.getCharacter())
                .transportation(country.getTransportation())
                .number(country.getNumber())
                .accident(country.getAccident())
                .culture(country.getCulture())
                .countryImage(country.getCountryImage())
                .build();
    }
}
