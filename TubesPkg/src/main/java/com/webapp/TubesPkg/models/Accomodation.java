package com.webapp.TubesPkg.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Accomodation extends BaseEntity {

    private String name;
    private String type;
    private double price;
    private String facility;
    private String address;
    private int rate;
    
}
