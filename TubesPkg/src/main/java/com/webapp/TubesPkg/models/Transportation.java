package com.webapp.TubesPkg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String type;
    private String provider;
    private int price;
    private String date;
    private String imageUrl;
    private String jam;
}
