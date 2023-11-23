package com.poductservice.productservice.controllers;

import com.poductservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        return ResponseEntity.internalServerError().body(productNotFoundException.getMessage());
    }
}
