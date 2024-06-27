package com.example.skhuthon_0th_team10.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountryListResDto {
    private Long countryId;
    private String name;
    private String countryImage;
}
