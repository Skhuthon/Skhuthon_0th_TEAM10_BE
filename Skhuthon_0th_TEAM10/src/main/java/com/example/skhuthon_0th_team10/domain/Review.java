package com.example.skhuthon_0th_team10.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "place_name1", nullable = false)
    private String placeName1;

    @Column(name = "place_info1", nullable = false)
    private String placeInfo1;

    @Column(name = "place_image1", nullable = false)
    private String placeImage1;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /*@ManyToOne
    @JoinColumn(name = "city_id")
    private City city;*/

    @Builder
    public Review(String placeName1, String placeInfo1, String placeImage1) {
        this.placeName1 = placeName1;
        this.placeInfo1 = placeInfo1;
        this.placeImage1 = placeImage1;
        //this.user= user;
    }

}
