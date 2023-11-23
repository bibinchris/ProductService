package com.poductservice.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class FakeStoreProductDto {
    //DTO : Data Transfer Object
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;


}
