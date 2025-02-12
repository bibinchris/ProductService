package com.poductservice.productservice.controllers;

import com.poductservice.productservice.exceptions.*;
import com.poductservice.productservice.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getPaymentById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return paymentService.getPaymentById(id);
    }
}
