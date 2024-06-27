package com.example.skhuthon_0th_team10.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Country {
    @Id
    @Column(name = "country_id")
    private long id;

    @Column(name = "country_name")
    private String name;

    @Column(name = "country_character")
    private String character;

    @Column(name = "country_transportation")
    private String transportation;

    @Column(name = "country_number")
    private String number;

    @Column(name = "country_accident")
    private String accident;

    @Column(name = "country_culture")
    private String culture;

    private String countryImage;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities = new ArrayList<>();
}
