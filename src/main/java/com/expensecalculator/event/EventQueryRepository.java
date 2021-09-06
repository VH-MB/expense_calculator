package com.expensecalculator.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface EventQueryRepository extends JpaRepository<Event, Long> {

    List<Event> findAll();

    Optional<Event> findByIdEvent(Long id);

}
