package com.example.skhuthon_0th_team10.repository;

import com.example.skhuthon_0th_team10.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<List<City>> findByCountryId(Long countryId);

}
