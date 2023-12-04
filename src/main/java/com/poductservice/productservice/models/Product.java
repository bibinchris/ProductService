package com.poductservice.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String tittle;
    private String description;
    private String image;
    @ManyToOne
    private Category category;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Price price;

}
