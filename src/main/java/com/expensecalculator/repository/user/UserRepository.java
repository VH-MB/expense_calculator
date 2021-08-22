package com.expensecalculator.repository.user;

import com.expensecalculator.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User save(User user);
}
