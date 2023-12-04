package com.poductservice.productservice.inheritanceRelations.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="st_mentors")
@DiscriminatorValue(value = "1")
public class Mentor extends User {
    private double avgRating;
}
