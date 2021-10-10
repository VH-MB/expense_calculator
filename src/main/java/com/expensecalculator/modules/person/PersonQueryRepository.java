package com.expensecalculator.modules.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonQueryRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);

    List<Person> findAll();

//   Optional<Person> findPersonByUsername(String username);
}
