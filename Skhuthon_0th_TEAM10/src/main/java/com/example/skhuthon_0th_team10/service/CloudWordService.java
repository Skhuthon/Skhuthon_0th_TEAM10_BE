package com.example.skhuthon_0th_team10.service;

import com.example.skhuthon_0th_team10.domain.Review;
import com.example.skhuthon_0th_team10.dto.CloudResponseDto;
import com.example.skhuthon_0th_team10.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudWordService {
    private final ReviewRepository reviewRepository;

    public CloudResponseDto getWordCloud() {
        List<Review> reviews = reviewRepository.findAll();

        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("Review", new Exception("userId로 review들을 찾을 수 없습니다."));
        }

        Map<String, Integer> reviewCounts = new HashMap<>();

        for (Review review : reviews) { // 장소 횟수 저장
            reviewCounts.put(review.getPlaceName1(),reviewCounts.getOrDefault(review.getPlaceName1(),0)+1);
        }

        // 리스트로 변환하는 작업
        List<Map<String,Object>> wordsList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : reviewCounts.entrySet()) {
            if (wordsList.size() >= 50) {
                break;
            }
            Map<String, Object> wordMap = new HashMap<>();
            wordMap.put("text", entry.getKey());
            wordMap.put("value", entry.getValue());
            wordsList.add(wordMap);
        }

        // 내림차순 정렬
        List<Map<String,Object>> sortedWordsList = wordsList.stream()
                .sorted((map1, map2) -> Integer.compare((Integer) map2.get("value"),(Integer) map1.get("value")))
                .toList();

        return  CloudResponseDto.create(
                sortedWordsList);
    }
}
