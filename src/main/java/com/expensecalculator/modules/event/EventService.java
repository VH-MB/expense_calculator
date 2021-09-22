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

    public List<Event> findAllEvent() {
        List<Event> events = eventQueryRepository.findAll();

        if (events == null) {
            return Collections.emptyList();
        }
        LOG.info("Downloads of all events!!!");
        return events;
    }

    public Event findByIdEvent(Long id) {
        return eventQueryRepository.findByIdEvent(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public Event createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setIdEvent(eventDto.getId());
        event.setName(eventDto.getName());

        Event createdEvent = eventRepository.save(event);
        LOG.info("Saving event: {}", event);
        return createdEvent;
    }

    public void remove(Long id) {
        eventRepository.deleteById(id);
    }
}
