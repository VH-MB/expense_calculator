package com.expensecalculator.user;

import com.expensecalculator.event.Event;
import com.expensecalculator.event.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final Logger logger = LogManager.getLogger(UserService.class);

    private UserRepository userRepository;
    private EventService eventService;

    @Autowired
    public UserService(UserRepository userRepository, EventService eventService) {
        this.userRepository = userRepository;
        this.eventService = eventService;
    }

   public User created(User newUser, Long id) {
        Optional<Event> eventOptional = eventService.searchForAnEventById(id);
        Event event;
        if (!eventOptional.isPresent()) {
            throw new RuntimeException("Can not event for id: " + id);
        }else {
            event = eventOptional.get();
        }
        User user = new User(newUser, event);
        userRepository.save(user);
        return user;
}

    Optional<User> findByIdUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId;
    }
}
