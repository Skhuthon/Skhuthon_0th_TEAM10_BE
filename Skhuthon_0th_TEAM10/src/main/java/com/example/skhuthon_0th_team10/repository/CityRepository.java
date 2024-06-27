package com.example.skhuthon_0th_team10.repository;

import com.example.skhuthon_0th_team10.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCountryId(Long countryId);

}
