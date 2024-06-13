package com.example.shopeefood.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JoinColumn(name = "city_id")
    @ManyToOne
    private City idCity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getIdCity() {
        return idCity;
    }

    public void setIdCity(City idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category() {
    }

    public Category(long id, City idCity, String name) {
        this.id = id;
        this.idCity = idCity;
        this.name = name;
    }
}