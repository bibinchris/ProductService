package com.poductservice.productservice.inheritanceRelations.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name="tpc_mentors")
public class Mentor extends User {
    private double avgRating;
}
