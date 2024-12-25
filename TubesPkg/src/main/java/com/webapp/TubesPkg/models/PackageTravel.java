package com.webapp.TubesPkg.models;

import jakarta.persistence.*;

@Entity
public class PackageTravel {
    @Id
    private String packageID;
    private String destination;
    private String facilities;
    private double price;

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_id", referencedColumnName = "id")
    private Transportation transportation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accomodation_id", referencedColumnName = "id")
    private Accomodation accomodation;

    
}
