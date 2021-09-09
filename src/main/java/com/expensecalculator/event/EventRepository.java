package com.expensecalculator.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

interface EventRepository extends JpaRepository<Event, Long> {

    Event save(Event event);

    @Transactional
    @Modifying
    void deleteById(Long id);
}

