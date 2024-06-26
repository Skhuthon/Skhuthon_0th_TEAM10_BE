package com.example.skhuthon_0th_team10.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
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

}
