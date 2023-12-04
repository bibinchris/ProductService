package com.poductservice.productservice.inheritanceRelations.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="j_mentors")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private double avgRating;
}
