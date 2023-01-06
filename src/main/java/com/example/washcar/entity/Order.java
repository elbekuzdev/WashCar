package com.example.washcar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    private Set<Service> services;
    @ManyToMany
    private Set<Washer> washers;
    private Double price;
    private String carModel;
    private String carNumber;
    private String clientName;
    private String clientNumber;
    private Boolean isActive;
    private Boolean isCancelled;
    private Date date;
    @ManyToOne
    private WashCompany washCompany;
}
