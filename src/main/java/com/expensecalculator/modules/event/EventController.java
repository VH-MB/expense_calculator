package com.expensecalculator.modules.event;


import com.expensecalculator.modules.event.dto.EventDto;
import com.expensecalculator.utils.payload.MessageResponse;
import com.expensecalculator.utils.validation.ResponseErrorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
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

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("event")
class EventController {

    private final EventService eventService;
    private final ResponseErrorValidation responseErrorValidation;

    @GetMapping("/all")
    ResponseEntity<List<EventDto>> getAllEvent() {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findAllEvent());
    }

    @GetMapping("/find/{id}")
    ResponseEntity<EventDto> getEventById(@PathVariable("id") String eventId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventService.findByIdEvent(Long.parseLong(eventId)));
    }

    @PostMapping("/add")
    ResponseEntity<Object> createEvent(@Valid @RequestBody EventDto eventDto,
                                       BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventDto));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<MessageResponse> deleteEventById(@PathVariable("id") String eventId) {
        eventService.remove(Long.parseLong(eventId));
        return new ResponseEntity<>(new MessageResponse("Event was deleted"), HttpStatus.OK);
    }
}
