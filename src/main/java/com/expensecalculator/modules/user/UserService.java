package com.expensecalculator.modules.user;

import com.expensecalculator.modules.event.Event;
import com.expensecalculator.modules.event.EventService;
import com.expensecalculator.modules.user.dto.UserDto;
import com.expensecalculator.modules.user.exeption.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;
    private final EventService eventService;

    List<User> findAllUser() {
        List<User> users = userQueryRepository.findAll();
        if (users == null) {
            return Collections.emptyList();
        }
        return users;
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userQueryRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    User createdUser(UserDto userDto, Long eventId) {
        Event event = eventService.findByIdEvent(eventId);

        User user = new User();
        user.setIdUser(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.addEvent(event);

        return userRepository.save(user);
    }

    public void remove(long userId) {
        userRepository.deleteById(userId);
    }
}
