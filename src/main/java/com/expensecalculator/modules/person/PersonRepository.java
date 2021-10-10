package com.expensecalculator.modules.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person save(Person person);

    @Transactional
    @Modifying
    void deleteById(Long personId);

}
