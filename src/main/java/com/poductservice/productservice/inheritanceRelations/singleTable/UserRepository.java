package com.poductservice.productservice.inheritanceRelations.singleTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_userrepository")
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);
}
