package com.poductservice.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.*;

import java.io.*;

@Data
@AllArgsConstructor
public class FakeStoreProductDto implements Serializable{
    //DTO : Data Transfer Object
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;


}
