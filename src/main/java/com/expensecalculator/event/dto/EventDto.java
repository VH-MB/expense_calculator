package com.expensecalculator.event.dto;

import com.expensecalculator.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class EventDto {

    @Id
    private Long id;
    private String name;
    private LocalDateTime startDataTime;
    private Set<UserDto> users;

}
