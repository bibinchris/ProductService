package com.poductservice.productservice.thirdPartyClients;

import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ThirdPartyInterface {
    FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException;
    List<FakeStoreProductDto> getAllProducts() throws ProductNotFoundException;
    FakeStoreProductDto deleteProductById(Long id) throws ProductNotFoundException;
    FakeStoreProductDto createProduct(GenericProductDto genericProductDto) throws ProductNotFoundException;
    FakeStoreProductDto updateProductById(Long id) throws ProductNotFoundException;
}
