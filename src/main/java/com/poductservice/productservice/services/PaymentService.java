package com.poductservice.productservice.services;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

@Service
public class PaymentService {
    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getPaymentById(Long id){
        ResponseEntity<String> value = restTemplate.getForEntity("http://paymentservice/payment/test/"+id, String.class);
        return value;
    }
}
