package com.poductservice.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//It won't initiate unnecessary dependencies.
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class  ProductControllerWebMVCTest {

    @MockBean
    private ProductService productService;
    @Inject
    private MockMvc mockMvc;
    @Inject
    private ObjectMapper objectMapper;
    @Test
    public void testGetAllProductReturnEmptyResult() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllProductReturn() throws Exception {
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        genericProductDtoList.add(GenericProductDto.builder().build());
        genericProductDtoList.add(GenericProductDto.builder().build());

        Mockito.when(productService.getAllProducts()).thenReturn(genericProductDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(genericProductDtoList)));
    }

    @Test
    public void createProductShouldCreateAValidProduct() throws Exception {
        GenericProductDto productToCreate =GenericProductDto.builder().build()
                .withTitle("MackBook")
                .withPrice(15000)
                .withDescription("Fastet Mackbook")
                .withCategory("Laptop");

        Mockito.when(productService.createProduct(productToCreate)).thenReturn(productToCreate);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(productToCreate)));
    }
}
