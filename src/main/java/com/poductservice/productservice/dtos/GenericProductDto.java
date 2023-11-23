package com.poductservice.productservice.dtos;

import lombok.*;

@Data
@Builder
@With
public class GenericProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;


}
