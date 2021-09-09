package com.expensecalculator.event;

import com.expensecalculator.event.dto.EventDto;
import com.expensecalculator.event.exception.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private static final Logger LOG = LogManager.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;
    private final EventFacade eventFacade;


    public List<EventDto> findAllEvent() {
        LOG.info("Downloads of all events!!!");
        return eventQueryRepository.findAll()
                .stream()
                .map(eventFacade::eventToEventDto)
                .collect(Collectors.toList());
    }

    public EventDto findByIdEvent(Long id) {
        return eventQueryRepository.findByIdEvent(id)
                .map(eventFacade::eventToEventDto)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public EventDto createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setIdEvent(eventDto.getId());
        event.setName(eventDto.getName());

        Event createdEvent = eventRepository.save(event);
        LOG.info("Saving event: {}", event);
        return eventFacade.eventToEventDto(createdEvent);
    }

    public void remove(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (EventNotFoundException e) {
            e = new EventNotFoundException(id);
        } finally {
            LOG.warn("Delete event");
        }
    }

    public Event getEventForUserById(Long id) {
        return eventQueryRepository.findByIdEvent(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }
}
