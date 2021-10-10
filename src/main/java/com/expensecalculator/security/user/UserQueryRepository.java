package com.expensecalculator.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdUser(Long id);

    Optional<User> findUserByEmail(String username);

    Optional<User> findUserByUsername(String username);

    Optional<String> findByUsername(String username);

    Optional<String> findByEmail(String email);

}
