package com.example.skhuthon_0th_team10.service;

import com.example.skhuthon_0th_team10.domain.Review;
import com.example.skhuthon_0th_team10.domain.User;
import com.example.skhuthon_0th_team10.dto.ReviewInfoResDto;
import com.example.skhuthon_0th_team10.dto.ReviewListResDto;
import com.example.skhuthon_0th_team10.dto.ReviewSaveReqDto;
import com.example.skhuthon_0th_team10.repository.ReviewRepository;
import com.example.skhuthon_0th_team10.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AwsS3Service awsS3Service;

    @Transactional
    public void reviewSave(ReviewSaveReqDto reviewSaveReqDto, MultipartFile multipartFile) throws IOException {
        String image = awsS3Service.upload(multipartFile, "review");
        //User user = userRepository.findById(reviewSaveReqDto.userId())
                //.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id =" + reviewSaveReqDto.userId()));

        Review review = Review.builder()
                .placeName1(reviewSaveReqDto.placeName1())
                .placeInfo1(reviewSaveReqDto.placeInfo1())
                .placeImage1(image)
                //.user(user)
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

    public ReviewInfoResDto reviewFindOne(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 후기가 없습니다. id = " + reviewId));
        return ReviewInfoResDto.from(review);
    }

}
