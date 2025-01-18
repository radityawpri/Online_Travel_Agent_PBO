package com.webapp.TubesPkg.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class BookingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String userName;
    private String userEmail;
    private String noTelp;
    private String paymentMethod;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private PackageTravel packageTravel;

    private double totalPrice;

}
