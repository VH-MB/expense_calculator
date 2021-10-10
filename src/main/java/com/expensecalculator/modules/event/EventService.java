package com.expensecalculator.modules.event;

import com.expensecalculator.modules.event.dto.EventDto;
import com.expensecalculator.modules.event.exception.EventNotFoundException;
import com.expensecalculator.security.user.User;
import com.expensecalculator.security.user.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private static final Logger LOG = LogManager.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;
    private final UserQueryRepository userQueryRepository;

    public List<Event> findAllEvent() {
        List<Event> events = eventQueryRepository.findAll();
        if (events == null) {
            return Collections.emptyList();
        }
        return events;
    }

    public Event findByIdEvent(Long id) {
        return eventQueryRepository.findByIdEvent(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public Event createEvent(EventDto eventDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        Event event = new Event();
        event.setIdEvent(eventDto.getId());
        event.setName(eventDto.getName());
        event.addUser(user);

        Event createdEvent = eventRepository.save(event);
        LOG.info("Saving Event for User: {}", user.getEmail());
        return createdEvent;
    }

    public void remove(Long id) {
        eventRepository.deleteById(id);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userQueryRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
