package com.expensecalculator;

import com.expensecalculator.event.Event;
import com.expensecalculator.event.EventService;
import com.expensecalculator.event.dto.EventDto;
import com.expensecalculator.user.UserService;
import com.expensecalculator.user.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class Starter implements CommandLineRunner {

    private EventService eventService;
    private UserService userService;

    public static Logger logger = LogManager.getLogger(Starter.class);

    @Autowired
    public Starter(EventService eventService, UserService userService) {
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
//        UserDto userDto = UserDto.builder()
//                .firstName("Volodymyr")
//                .lastName("Holovetskyi")
//                .build();
//
        EventDto event = EventDto.builder()
                .name("Wycieczka w gury")
                .build();
//        event.addUserDto(userDto);

//        event.addUser(user1);
//        event.addUser(user2);
//        Event event1 = new Event("Wycieczka do Warszawy");
//        event1.addUser(user2ser3);
//        event1.addUser(user4);
//
        EventDto event1 = eventService.createEvent(event);
//        Event events1 = eventService.created(event1);
//        Optional<Event> allEvent = eventFacade.findByIdEvent(2L);
//        eventFacade.remove(1L);

        logger.info("Wydażenia {}", event1);


    }
}
