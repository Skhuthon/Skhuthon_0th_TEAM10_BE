package com.example.skhuthon_0th_team10.dto;

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
}
