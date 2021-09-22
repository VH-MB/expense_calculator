package com.expensecalculator.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    List<User> findAll();

    Optional<User> findUserByEmail(String username);
}
