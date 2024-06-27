package com.example.skhuthon_0th_team10.service;

import com.example.skhuthon_0th_team10.domain.City;
import com.example.skhuthon_0th_team10.domain.Review;
import com.example.skhuthon_0th_team10.domain.User;
import com.example.skhuthon_0th_team10.dto.ReviewInfoResDto;
import com.example.skhuthon_0th_team10.dto.ReviewListResDto;
import com.example.skhuthon_0th_team10.dto.ReviewSaveReqDto;
import com.example.skhuthon_0th_team10.repository.CityRepository;
import com.example.skhuthon_0th_team10.repository.ReviewRepository;
import com.example.skhuthon_0th_team10.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final AwsS3Service awsS3Service;

    @Transactional
    public void reviewSave(ReviewSaveReqDto reviewSaveReqDto, MultipartFile multipartFile, Long cityId, Principal principal) throws IOException {
        String image = awsS3Service.upload(multipartFile, "review");
        City city = cityRepository.findById(cityId).orElseThrow();
        Long id = Long.parseLong(principal.getName());

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id =" + id));

        Review review = Review.builder()
                .placeName1(reviewSaveReqDto.placeName1())
                .placeInfo1(reviewSaveReqDto.placeInfo1())
                .placeImage1(image)
                .user(user)
                .city(city)
                .build();
        reviewRepository.save(review);

    }

    public ReviewListResDto reviewFindAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewInfoResDto> reviewInfoResDtoList = reviews.stream()
                .map(ReviewInfoResDto::from)
                .toList();
        return ReviewListResDto.from(reviewInfoResDtoList);
    }

    public List<ReviewInfoResDto> findReviewList(Long cityId) {
        List<Review> reviews = reviewRepository.findByCityId(cityId)
                .orElseThrow();

        List<ReviewInfoResDto> reviewInfoResDtoList = new ArrayList<>();

        for (Review review : reviews) {
            ReviewInfoResDto reviewInfoResDto = new ReviewInfoResDto(
                    review.getId(),
                    review.getPlaceName1(),
                    review.getPlaceInfo1(),
                    review.getPlaceImage1()
            );

            reviewInfoResDtoList.add(reviewInfoResDto);
        }
        return reviewInfoResDtoList;
    }

}
