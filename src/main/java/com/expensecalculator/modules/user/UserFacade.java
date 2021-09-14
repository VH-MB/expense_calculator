package com.expensecalculator.modules.user;

import com.expensecalculator.modules.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getIdUser())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
