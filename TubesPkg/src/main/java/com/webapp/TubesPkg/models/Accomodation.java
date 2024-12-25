package com.webapp.TubesPkg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accomodation {
    @Id
    private String id;
    private String type;
    private String name;
    private double price;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}
