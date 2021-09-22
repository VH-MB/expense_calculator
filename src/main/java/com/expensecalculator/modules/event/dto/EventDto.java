package com.expensecalculator.modules.event.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {

    @Id
    private Long id;
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;
    private LocalDateTime startDataTime;
}
