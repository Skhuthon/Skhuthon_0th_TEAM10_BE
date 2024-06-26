package com.example.skhuthon_0th_team10.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
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

}
