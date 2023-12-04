package com.poductservice.productservice.inheritanceRelations.tablePerClass;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
}
