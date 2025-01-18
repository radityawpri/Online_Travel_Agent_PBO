package com.webapp.TubesPkg.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wahana extends BaseEntity{

    private String name;
    private int price;
}