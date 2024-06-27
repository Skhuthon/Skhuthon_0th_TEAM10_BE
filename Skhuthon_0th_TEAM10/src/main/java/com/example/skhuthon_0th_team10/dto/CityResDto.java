package com.example.skhuthon_0th_team10.dto;

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
}
