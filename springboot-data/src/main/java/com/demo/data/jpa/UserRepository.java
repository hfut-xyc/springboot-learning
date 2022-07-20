package com.demo.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Author, Integer> {

    Author findUserById(Integer id);

    Author findByUsername(String username);

}
