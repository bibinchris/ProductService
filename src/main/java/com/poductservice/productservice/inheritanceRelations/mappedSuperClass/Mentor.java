package com.poductservice.productservice.inheritanceRelations.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity(name="ms_mentors")
public class Mentor extends User{
    private double avgRating;
}
