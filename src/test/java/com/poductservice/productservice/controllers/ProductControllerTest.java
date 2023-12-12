package com.poductservice.productservice.controllers;

import com.poductservice.productservice.exceptions.ProductNotFoundException;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductControllerTest {

    @Inject
    ProductController productController;

    @Test
    void testGetProductById() throws ProductNotFoundException {
        assertNotNull(productController.getProductById(1L));
    }

    @Test
    void testGetProductById_Exception(){
       assertThrows(ProductNotFoundException.class, ()->productController.getProductById(1000L));
    }


}
