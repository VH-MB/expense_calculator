package com.expensecalculator.modules.event.dto;

import com.expensecalculator.shared.validation.ValidationMessages;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {

    @Id
    private Long id;

    @NotBlank(message = ValidationMessages.EVENT_NAME_REQUIRED)
    private String name;

    private LocalDateTime startDataTime;
}
