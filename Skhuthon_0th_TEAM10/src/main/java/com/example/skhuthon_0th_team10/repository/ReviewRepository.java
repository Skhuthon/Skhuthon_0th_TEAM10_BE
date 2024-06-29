package com.example.skhuthon_0th_team10.repository;

import com.example.skhuthon_0th_team10.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findByCityId(Long cityId);

    Optional<List<Review>> findByUserId(Long UserId);
}
