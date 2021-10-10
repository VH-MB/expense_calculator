package com.expensecalculator.modules.event;


import com.expensecalculator.modules.event.dto.EventDto;
import com.expensecalculator.security.payload.response.MessageResponse;
import lombok.RequiredArgsConstructor;
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
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("event")
class EventController {

    private final EventService eventService;
    private final EventFacade eventFacade;

    @GetMapping("/all")
    ResponseEntity<List<EventDto>> getAllEvent() {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findAllEvent()
                .stream().map(eventFacade::eventToEventDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/find/{id}")
    ResponseEntity<EventDto> getEventById(@PathVariable("id") String eventId) {
        Event event = eventService.findByIdEvent(Long.parseLong(eventId));
        return ResponseEntity.status(HttpStatus.OK).body(eventFacade.eventToEventDto(event));
    }

    @PostMapping("/add")
    ResponseEntity<Object> createEvent(@Valid @RequestBody EventDto eventDto, Principal principal) {

        Event event = eventService.createEvent(eventDto, principal);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventFacade.eventToEventDto(event));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<MessageResponse> deleteEventById(@PathVariable("id") String eventId) {
        eventService.remove(Long.parseLong(eventId));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Event was deleted"));
    }
}
