package com.poductservice.productservice.services;

public interface ProductService {
    String getProductById(Long id);
    void getAllProducts();
    void deleteProductById(Long id);
    void createProduct();
    void updateProductById(Long id);

}
