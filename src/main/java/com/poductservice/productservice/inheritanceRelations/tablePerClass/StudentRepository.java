package com.poductservice.productservice.inheritanceRelations.tablePerClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_studentrepository")
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    Student save(Student student);
}
