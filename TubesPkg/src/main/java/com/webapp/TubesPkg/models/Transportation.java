package com.webapp.TubesPkg.models;



import com.webapp.TubesPkg.service.PackageTravelService;

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
    private Long id;
    private String type;
    private String provider;
    private double price;
    public void setPackageTravel(PackageTravelService packageTravel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPackageTravel'");
    }

   }