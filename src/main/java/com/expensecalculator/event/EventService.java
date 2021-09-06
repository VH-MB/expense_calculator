package com.expensecalculator.event;

import com.expensecalculator.event.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private static final Logger LOG = LogManager.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;
    private final EventMapper eventMapper;


    public List<EventDto> getAllEvent() {
        List<Event> events = eventQueryRepository.findAll();
        List<EventDto> eventsDto = eventMapper.eventsToEventsDto(events);
        LOG.info("Get all events {}", eventsDto);
        return eventsDto;
    }

    public EventDto findByIdEvent(Long id) {
        Optional<Event> event = eventQueryRepository.findByIdEvent(id);

        if (!event.isPresent()) {
            throw new RuntimeException("Can not event with id: " + id);
        }
        EventDto eventDto = eventMapper.eventToEventDto(event.get());
        LOG.info("Lista wydażen: {}", event.get());
        return eventDto;
    }

    public Optional<Event> searchForAnEventById(Long id) {
        return eventQueryRepository.findByIdEvent(id);
    }

    public EventDto created(EventDto eventDto) {
        Event event = eventMapper.eventDtoToEvent(eventDto);
        Event newEvent = eventRepository.save(event);
        return eventMapper.eventToEventDto(newEvent);
    }

    public void remove(Long id) {
        eventRepository.deleteById(id);
//        logger.warn("Delete event by id: {}", delete);
    }

}
