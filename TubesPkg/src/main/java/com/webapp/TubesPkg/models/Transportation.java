package com.webapp.TubesPkg.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transportation extends BaseEntity{

    private String type;
    private String provider;
    private int price;
    private String date;
    private String jam;
}
