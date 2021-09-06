package com.expensecalculator.event;


import com.expensecalculator.event.dto.EventDto;
import com.expensecalculator.payload.MessageResponse;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("event")
class EventController {

    private final EventService eventService;

    @GetMapping("/all")
    ResponseEntity<List<EventDto>> getAllEvent() {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvent());
    }

    @GetMapping("/find/{id}")
    ResponseEntity<EventDto> getEventById(@PathVariable("id") String eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findByIdEvent(Long.parseLong(eventId)));
    }

    @PostMapping("/add")
    ResponseEntity<EventDto> createdEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.created(eventDto));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<MessageResponse> deleteEventById(@PathVariable("id") String eventId) {
        eventService.remove(Long.parseLong(eventId));
        return new ResponseEntity<>(new MessageResponse("Event was deleted"), HttpStatus.OK);
    }
}
