package com.poductservice.productservice.services;

import ch.qos.logback.core.util.PropertySetterException;
import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws ProductNotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(Long id);
    GenericProductDto createProduct(GenericProductDto genericProductDto);
    GenericProductDto updateProductById(Long id) throws ProductNotFoundException;

}
