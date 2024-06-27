package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.CountryListResDto;
import com.example.skhuthon_0th_team10.dto.CountryResDto;
import com.example.skhuthon_0th_team10.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    @GetMapping("/country/{countryId}") // 단일 조회
    public ResponseEntity<CountryResDto> getCountryInfo(@PathVariable Long countryId) {
                return new ResponseEntity<>(countryService.getCountryInfo(countryId), HttpStatus.OK);
    }

    @GetMapping("/country") // 전체 조회
    public ResponseEntity<List<CountryListResDto>> getCountryList() {
        return new ResponseEntity<>(countryService.getCountryList(), HttpStatus.OK);
    }
}
