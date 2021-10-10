package com.expensecalculator.modules.person;

import com.expensecalculator.modules.person.dto.PersonDto;
import com.expensecalculator.security.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
class PersonController {

    private final PersonService personService;
    private final PersonFacade personFacade;

    @Autowired
    public PersonController(final PersonService personService,
                            final PersonFacade personFacade) {
        this.personService = personService;
        this.personFacade = personFacade;
    }

    @GetMapping("/all")
    ResponseEntity<List<PersonDto>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.findAllUser()
                        .stream()
                        .map(personFacade::personToPersonDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/find/{userId}")
    ResponseEntity<PersonDto> getUserById(@PathVariable("userId") String personId) {
        Person person = personService.findPersonById(Long.parseLong(personId));
        return ResponseEntity.status(HttpStatus.OK).body(personFacade.personToPersonDto(person));
    }

    @PostMapping("/add/{eventId}")
    ResponseEntity<Object> createUser(@Valid @RequestBody PersonDto userDto,
                                      @PathVariable("eventId") String eventId) {
        Person person = personService.createdUser(userDto, Long.parseLong(eventId));
        return ResponseEntity.status(HttpStatus.CREATED).body(personFacade.personToPersonDto(person));
    }

    @DeleteMapping("/remove/{userId}")
    ResponseEntity<MessageResponse> deleteUserById(@PathVariable("userId") String userId) {
        personService.remove(Long.parseLong(userId));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Person was deleted"));
    }

}

