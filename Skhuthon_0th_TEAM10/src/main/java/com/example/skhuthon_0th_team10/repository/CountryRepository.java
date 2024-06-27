package com.example.skhuthon_0th_team10.repository;

import com.example.skhuthon_0th_team10.domain.Country;
import com.example.skhuthon_0th_team10.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findById(Long id);

}
