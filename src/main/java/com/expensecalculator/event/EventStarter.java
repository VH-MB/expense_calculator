package com.expensecalculator.event;

import com.expensecalculator.user.UserService;
import com.expensecalculator.user.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class EventStarter implements CommandLineRunner {

    private EventService eventService;
    private UserService userService;

    public static Logger logger = LogManager.getLogger(EventStarter.class);

    @Autowired
    public EventStarter(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

//        User user1 = new User("Volodymyr", "Holovetskyi");//2
//        User user2 = new User("Mykola", "Olchowetskyi");
//        User user3 = new User("Mariia", "Khort");
//        User user4 = new User("Bogdan", "Bentsal");
//
//
////        userService.created(user, 1L);
////        userService.created(user2, 1L);
////        userService.created(user3, 2L);
////        userService.created(user4, 2L);
//
//        Event event = new Event("Wycieczka w gury");
//        event.addUser(user1);
//        event.addUser(user2);
//        Event event1 = new Event("Wycieczka do Warszawy");
//        event1.addUser(user3);
//        event1.addUser(user4);
//
//        Event events = eventService.created(event);
//        Event events1 = eventService.created(event1);
//        Optional<Event> allEvent = eventFacade.findByIdEvent(2L);
//        eventFacade.remove(1L);

//            logger.info("Wydażenia {}", events);

        UserDto userDto = UserDto.builder()
                .firstName("Volodymyr")
                .lastName("Holovetskyi")
                .build();


    }
}
