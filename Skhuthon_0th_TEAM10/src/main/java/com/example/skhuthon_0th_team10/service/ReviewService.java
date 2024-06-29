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
import jakarta.persistence.EntityNotFoundException;
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

        Review review = reviewSaveReqDto.toEntity(user, city, image);
        reviewRepository.save(review);

    }

    public ReviewListResDto reviewFindAll(Principal principal) {
        Long id = Long.parseLong(principal.getName());
        List<Review> reviews = reviewRepository.findByUserId(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("review", new Exception("userId로 review를 찾을 수 없습니다.")));
        ;

        List<ReviewInfoResDto> reviewInfoResDtoList = reviews.stream()
                .map(ReviewInfoResDto::from)
                .toList();
        return ReviewListResDto.from(reviewInfoResDtoList);
    }

    public List<ReviewInfoResDto> findReviewList(Long cityId) {
        List<Review> reviews = reviewRepository.findByCityId(cityId)
                .orElseThrow(()
                        -> new EntityNotFoundException("review", new Exception("cityId로 review를 찾을 수 없습니다.")));


        List<ReviewInfoResDto> reviewInfoResDtoList = new ArrayList<>();

        for (Review review : reviews) {
            ReviewInfoResDto reviewInfoResDto = ReviewInfoResDto.from(review);

            reviewInfoResDtoList.add(reviewInfoResDto);
        }
        return reviewInfoResDtoList;
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow( () -> new IllegalArgumentException("해당 리뷰가 없습니다. id = " + reviewId));

        reviewRepository.delete(review);
    }
}
