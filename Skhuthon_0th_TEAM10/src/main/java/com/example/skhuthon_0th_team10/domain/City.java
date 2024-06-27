package com.example.skhuthon_0th_team10.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class City {
    @Id
    @Column(name = "city_id")
    private long id;

    @Column(name = "city_name")
    private String name;

    @Column(name = "city_info")
    private String info;

    @Column(name = "city_recommendation")
    private String recommendation;

    private String cityImage;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
