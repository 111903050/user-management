package com.management.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    public int id;
    @Column(name = "STREET_ADDRESS")
    public String streetAddress;
    @Column(name = "CITY")
    public String city;
    @Column(name = "STATE")
    public String state;
    @Column(name = "POSTAL_CODE")
    public String postalCode;
    @Column(name = "COUNTRY")
    public String country;
}
