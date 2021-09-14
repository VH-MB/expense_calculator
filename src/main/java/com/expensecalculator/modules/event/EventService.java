package com.expensecalculator.modules.event;

import com.expensecalculator.modules.event.dto.EventDto;
import com.expensecalculator.modules.event.exception.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private static final Logger LOG = LogManager.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;
    private final EventFacade eventFacade;


    List<EventDto> findAllEvent() {
        List<Event> allEvent = eventQueryRepository.findAll();

        if (allEvent == null) {
            LOG.warn("List events is empty");
            return Collections.emptyList();
        }
        LOG.info("Downloads of all events!!!");
        return allEvent.stream()
                .map(eventFacade::eventToEventDto)
                .collect(Collectors.toList());
    }

    EventDto findByIdEvent(Long id) {
        return eventQueryRepository.findByIdEvent(id)
                .map(eventFacade::eventToEventDto)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    EventDto createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setIdEvent(eventDto.getId());
        event.setName(eventDto.getName());

        Event createdEvent = eventRepository.save(event);
        LOG.info("Saving event: {}", event);

        return eventFacade.eventToEventDto(createdEvent);
    }

    void remove(Long id) {
        eventRepository.deleteById(id);
    }

    public Event getEventForUserById(Long id) {
        return eventQueryRepository.findByIdEvent(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }
}
