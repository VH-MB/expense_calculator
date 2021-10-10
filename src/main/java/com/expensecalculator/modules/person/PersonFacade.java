package com.expensecalculator.modules.person;

import com.expensecalculator.modules.person.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
class PersonFacade {

    public PersonDto personToPersonDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
    }
}
