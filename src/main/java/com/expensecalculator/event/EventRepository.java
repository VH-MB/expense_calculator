package com.expensecalculator.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

interface EventRepository extends JpaRepository<Event, Long> {

    Event save(Event event);


//    void deleteEventById(Long id);

    @Transactional
    @Modifying
    void deleteById(Long id);
}

