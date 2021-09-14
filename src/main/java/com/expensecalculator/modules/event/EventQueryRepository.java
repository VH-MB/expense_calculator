package com.expensecalculator.modules.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
interface EventQueryRepository extends JpaRepository<Event, Long> {

    List<Event> findAll();

    Optional<Event> findByIdEvent(Long id);

}
