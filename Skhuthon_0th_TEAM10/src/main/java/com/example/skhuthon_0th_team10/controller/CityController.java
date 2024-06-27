package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.CityListResDto;
import com.example.skhuthon_0th_team10.dto.CityResDto;
import com.example.skhuthon_0th_team10.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/citys/{countryId}")
    public ResponseEntity<List<CityListResDto>> getCityList(@PathVariable Long countryId) {
        return new ResponseEntity<>(cityService.getCityList(countryId), HttpStatus.OK);
    }

    @GetMapping("city/{cityId}")
    public ResponseEntity<CityResDto> getCityInfo(@PathVariable Long cityId) {
        return new ResponseEntity<>(cityService.getCityInfo(cityId),HttpStatus.OK);
    }
}
