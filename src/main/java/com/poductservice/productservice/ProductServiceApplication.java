package com.poductservice.productservice;

import com.poductservice.productservice.aop.ExecutionTimeLogger;
import com.poductservice.productservice.inheritanceRelations.singleTable.*;
import com.poductservice.productservice.models.Category;
import com.poductservice.productservice.models.Price;
import com.poductservice.productservice.repositories.PriceRepository;
import com.poductservice.productservice.models.Product;
import com.poductservice.productservice.repositories.CategoryRepository;
import com.poductservice.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableAspectJAutoProxy
@RestController
@EnableDiscoveryClient
public class ProductServiceApplication {//implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @RequestMapping(value = "/")
    public String home() {
        return "Eureka Client application";
    }
}
