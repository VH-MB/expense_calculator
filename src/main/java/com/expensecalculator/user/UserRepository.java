package com.expensecalculator.user;

import com.expensecalculator.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

   Optional<User> findById(Long id);
}
