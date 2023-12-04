package com.poductservice.productservice.inheritanceRelations.singleTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_studentrepository")
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    Student save(Student student);
}
