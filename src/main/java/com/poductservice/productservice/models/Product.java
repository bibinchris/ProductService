package com.poductservice.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String tittle;
    private String desc;
    private int price;
    private String image;
    private Category category;
}
