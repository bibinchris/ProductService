package com.poductservice.productservice.controllers;

import com.poductservice.productservice.exceptions.ProductNotFoundException;
import com.poductservice.productservice.thirdPartyClients.fakeStoreClient.FakeStoreClient;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductControllerTest {

    @Inject
    ProductController productController;
    @Inject
    FakeStoreClient fakeStoreClient;

    @Test
    void testGetProductById(){
       assertThrows(ProductNotFoundException.class, ()->productController.getProductById(1000L));
    }
}
