package com.example.skhuthon_0th_team10.service;

import com.example.skhuthon_0th_team10.domain.Country;
import com.example.skhuthon_0th_team10.dto.CountryListResDto;
import com.example.skhuthon_0th_team10.dto.CountryResDto;
import com.example.skhuthon_0th_team10.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryResDto getCountryInfo(Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(()
                        -> new EntityNotFoundException("country", new Exception("country를 찾을 수 없습니다.")));
        ;

        CountryResDto countryResDto = CountryResDto.from(country);

        return countryResDto;
    }

    public List<CountryListResDto> getCountryList() {
        List<Country> countries = countryRepository.findAll();

        List<CountryListResDto> countryListResDtos = new ArrayList<>();

        for (Country country : countries) {
            CountryListResDto countryListResDto = CountryListResDto.from(country);

            countryListResDtos.add(countryListResDto);
        }

        return countryListResDtos;
    }
}
