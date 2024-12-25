package com.webapp.TubesPkg.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class PackageTravel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageID;
    private String destination;
    private String facilities;
    private double price;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_id", referencedColumnName = "id")
    private Transportation transportation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accomodation_id", referencedColumnName = "id")
    private Accomodation accomodation;

    

    
}
