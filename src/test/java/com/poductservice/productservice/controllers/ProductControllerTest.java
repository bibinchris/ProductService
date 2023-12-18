package com.poductservice.productservice.controllers;

import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;
import com.poductservice.productservice.services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ProductControllerTest {

    @Inject
    ProductController productController;

    @Mock
    ProductService productService;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    @Test
    void testGetProductById() throws ProductNotFoundException {
        Assertions.assertNotNull(productController.getProductById(1L));
    }

    @Test
    void testGetProductById_Exception(){
        Assertions.assertThrows(ProductNotFoundException.class, ()->productController.getProductById(1000L));
    }

    @Test
    void testGetProductById_Mocking() throws ProductNotFoundException {
        productController = new ProductController(productService);
        Mockito.when(productService.getProductById(Mockito.anyLong())).thenReturn(convertToGenericProductDto());
        GenericProductDto genericProductDto = productController.getProductById(1000L);
        Assertions.assertNotNull(genericProductDto);
        Assertions.assertEquals(genericProductDto.getId(), 1000L);
        Assertions.assertEquals(genericProductDto.getTitle(), "testTitle");
        Assertions.assertEquals(genericProductDto.getPrice(), 15000);
        Assertions.assertEquals(genericProductDto.getDescription(), "testDesc");
    }

    @Test
    void testGetProductById_MockingException() throws ProductNotFoundException {
        productController = new ProductController(productService);
        Mockito.when(productService.getProductById(Mockito.anyLong())).thenThrow(ProductNotFoundException.class);

        Assertions.assertThrows(ProductNotFoundException.class, ()->productController.getProductById(10010L));
    }

    @Test
    public void testProductControllerCallsProductServiceWithSameId() throws ProductNotFoundException {
        //This is the test case to check ProductController is passing same id to ProductService
        Long id = 100l;
        productController = new ProductController(productService);
        Mockito.when(productService.getProductById(id)).thenReturn(GenericProductDto.builder().build());
        GenericProductDto genericProductDto = productController.getProductById(id);
        Mockito.verify(productService).getProductById(argumentCaptor.capture());
        Assertions.assertEquals(id, argumentCaptor.getValue());
    }

    private GenericProductDto convertToGenericProductDto() {
        return GenericProductDto.builder().build()
                .withId(1000L)
                .withTitle("testTitle")
                .withPrice(15000)
                .withCategory(null)
                .withDescription("testDesc")
                .withImage(null);
    }

}
