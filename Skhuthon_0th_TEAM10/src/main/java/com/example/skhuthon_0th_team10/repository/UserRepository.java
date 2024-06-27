package com.example.skhuthon_0th_team10.repository;

import com.example.skhuthon_0th_team10.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userEmail);
    Optional<User> findById(Long userId);
}
