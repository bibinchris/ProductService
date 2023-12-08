package com.poductservice.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel{
    private String tittle;
    private String description;
    private String image;
    @ManyToOne
    private Category category;

    /**
     * CascadeType.REMOVE = if the product is deleted
     * from database price will be automatically deleted.
     */
    @OneToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(nullable = false)
    private Price price;

}
