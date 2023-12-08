package com.poductservice.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Category extends BaseModel{
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Here Product is loaded lazy
     * @Transactional annotation is used to call it explicitly
     * or make it eager loading
     */
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
