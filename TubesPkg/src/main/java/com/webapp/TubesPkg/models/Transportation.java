package com.webapp.TubesPkg.models;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transportation {
    @Id
    private String id;
    private String type;
    private String provider;
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
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


   }