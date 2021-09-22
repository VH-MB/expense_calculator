package com.expensecalculator.modules.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface EventQueryRepository extends JpaRepository<Event, Long> {

    List<Event> findAll();

    Optional<Event> findByIdEvent(Long id);

}
