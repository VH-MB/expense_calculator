package com.expensecalculator.modules.event.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {

    @Id
    private Long id;
    @NotBlank(message = "Can not null")
    private String name;
    private LocalDateTime startDataTime;
}
