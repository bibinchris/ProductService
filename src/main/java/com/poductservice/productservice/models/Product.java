package com.poductservice.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String tittle;
    private String description;
    private int price;
    private String image;
    @ManyToOne
    private Category category;
}
