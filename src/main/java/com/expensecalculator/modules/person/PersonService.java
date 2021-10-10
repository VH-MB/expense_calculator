package com.expensecalculator.modules.person;


import com.expensecalculator.modules.event.Event;
import com.expensecalculator.modules.event.EventService;
import com.expensecalculator.modules.person.dto.PersonDto;
import com.expensecalculator.modules.person.exeption.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {

    private static final Logger LOG = LogManager.getLogger(PersonService.class);

    private final PersonRepository personRepository;
    private final PersonQueryRepository personQueryRepository;
    private final EventService eventService;

    List<Person> findAllUser() {
        List<Person> people = personQueryRepository.findAll();
        if (people == null) {
            return Collections.emptyList();
        }
        return people;
    }

    public Person findPersonById(Long id) throws PersonNotFoundException {
        return personQueryRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    Person createdUser(PersonDto userDto, Long eventId) {
        Event event = eventService.findByIdEvent(eventId);

        Person user = new Person();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.addEvent(event);

        LOG.info("Saving person for Event: {}", event.getIdEvent());
        return personRepository.save(user);
    }

    public void remove(long userId) {
        personRepository.deleteById(userId);
    }
}
