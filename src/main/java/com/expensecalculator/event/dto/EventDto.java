package com.expensecalculator.event.dto;

import com.expensecalculator.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
public class EventDto {

    @Id
    private Long id;
    private String name;
    private LocalDateTime startDataTime;
    private Set<UserDto> users;

    public void addUserDto(UserDto userDto) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(userDto);
    }
}
