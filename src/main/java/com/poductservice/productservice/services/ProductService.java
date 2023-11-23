package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id);
    List<GenericProductDto> getAllProducts();
    void deleteProductById(Long id);
    GenericProductDto createProduct(GenericProductDto genericProductDto);
    void updateProductById(Long id);

}
