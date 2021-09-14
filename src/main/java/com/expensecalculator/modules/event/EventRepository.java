package com.expensecalculator.modules.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@RepositoryRestResource
interface EventRepository extends JpaRepository<Event, Long> {

    Event save(Event event);

    @Transactional
    @Modifying
    void deleteById(Long id);
}

