package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws ProductNotFoundException;
    List<GenericProductDto> getAllProducts() throws ProductNotFoundException;
    GenericProductDto deleteProductById(Long id) throws ProductNotFoundException;
    GenericProductDto createProduct(GenericProductDto genericProductDto) throws ProductNotFoundException;
    GenericProductDto updateProductById(Long id) throws ProductNotFoundException;

}
