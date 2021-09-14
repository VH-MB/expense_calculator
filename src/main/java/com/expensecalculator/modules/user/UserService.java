package com.expensecalculator.modules.user;

import com.expensecalculator.modules.event.Event;
import com.expensecalculator.modules.event.EventService;
import com.expensecalculator.modules.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;
    private final EventService eventService;
    private final UserFacade userFacade;

    List<UserDto> findAllUser() {
        List<User> allUser = userQueryRepository.findAll();
        if (allUser == null) {
            return Collections.emptyList();
        }
        return allUser.stream()
                .map(userFacade::userToUserDto)
                .collect(Collectors.toList());
    }

    Optional<User> findByIdUser(Long id) {
        Optional<User> byId = userQueryRepository.findById(id);
        return byId;
    }

    public User createdUser(User newUser, Long id) {
        Event event = eventService.getEventForUserById(id);
        User user = new User(newUser, event);
        userRepository.save(user);
        return user;
    }
}
