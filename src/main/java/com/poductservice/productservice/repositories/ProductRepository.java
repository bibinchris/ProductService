package com.poductservice.productservice.repositories;

import com.poductservice.productservice.aop.ExecutionTimeLogger;
import com.poductservice.productservice.models.Product;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.*;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    public List<Product> findByTittle(String title, Pageable pageable);

    public List<Product> findAllByTittleContainingIgnoreCase(String title, Pageable pageable);
    public List<Product> findByTittleAndDescription(String title, String desc);
    @ExecutionTimeLogger
    public List<Product> findByPrice_ValueGreaterThan(Integer value);

    @ExecutionTimeLogger
    @Query(value = "select * from product", nativeQuery = true)
    public List<Product> findAllProductByCustomQuery();
}
