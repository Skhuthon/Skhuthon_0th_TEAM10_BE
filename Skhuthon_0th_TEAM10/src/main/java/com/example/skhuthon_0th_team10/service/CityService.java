package com.example.skhuthon_0th_team10.service;

import com.example.skhuthon_0th_team10.domain.City;
import com.example.skhuthon_0th_team10.dto.CityListResDto;
import com.example.skhuthon_0th_team10.dto.CityResDto;
import com.example.skhuthon_0th_team10.repository.CityRepository;
import com.example.skhuthon_0th_team10.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<CityListResDto> getCityList(Long countryId) {
        List<City> cities = cityRepository.findByCountryId(countryId)
                .orElseThrow(()
                        -> new EntityNotFoundException("city", new Exception("countryId로 city를 찾을 수 없습니다.")));
        ;

        List<CityListResDto> cityListResDtos = new ArrayList<>();

        for (City city : cities) {
            CityListResDto cityListResDto = CityListResDto.from(city);

            cityListResDtos.add(cityListResDto);
        }

        return cityListResDtos;
    }

    public CityResDto getCityInfo(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(()
                -> new EntityNotFoundException("city", new Exception("city를 찾을 수 없습니다.")));

        CityResDto cityResDto = CityResDto.from(city);

        return cityResDto;
    }

}
