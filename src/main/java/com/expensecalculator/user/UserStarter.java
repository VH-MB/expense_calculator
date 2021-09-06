package com.expensecalculator.user;

import com.expensecalculator.event.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserStarter implements CommandLineRunner {

    private final UserService userService;
    private final EventService eventService;

    @Override
    public void run(String... args) throws Exception {

        final Logger logger = LogManager.getLogger(UserStarter.class);

//        User user = new User("Volodymyr", "Holovetskyi");//2
//        User user2 = new User("Mykola", "Olchowetskyi");
//        User user3 = new User("Mariia", "Khort");
//        User user4 = new User("Bogdan", "Bentsal");
//
//
//        userService.created(user, 1L);
//        userService.created(user2, 1L);
//        userService.created(user3, 2L);
//        userService.created(user4, 2L);
//        Optional<Event> byIdEvent = eventFacade.findByIdEvent(2L);
//        Optional<User> byIdUser = userService.findByIdUser(2L);
//        Event event = byIdEvent.get();
//        User userById = byIdUser.get();
//        logger.info("Event: {} ", event);
//        logger.info("User: {}", userById);
    }
}
