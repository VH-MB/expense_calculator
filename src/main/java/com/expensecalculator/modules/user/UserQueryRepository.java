package com.expensecalculator.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface UserQueryRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findAll();

}
