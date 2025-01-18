package com.webapp.TubesPkg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PackageTravel extends BaseEntity{
 
    private String name; 
    private double totalPrice;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "transportation_id", referencedColumnName = "id")
    private Transportation transportation;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "accomodation_id", referencedColumnName = "id")
    private Accomodation accomodation;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "wahana_id", referencedColumnName = "id")
    private Wahana wahana;


}
