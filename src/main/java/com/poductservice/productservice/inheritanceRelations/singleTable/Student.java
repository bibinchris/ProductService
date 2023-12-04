package com.poductservice.productservice.inheritanceRelations.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="st_student")
@DiscriminatorValue(value = "2")
public class Student extends User {
    private double psp;
}
