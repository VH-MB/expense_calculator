package com.expensecalculator.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    @Transactional
    @Modifying
    void deleteById(Long userId);

}
