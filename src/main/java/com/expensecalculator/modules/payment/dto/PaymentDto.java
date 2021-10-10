package com.expensecalculator.modules.payment.dto;

import com.expensecalculator.shared.validation.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {

    @Id
    private Long id;

    @NotEmpty(message = ValidationMessages.DESCRIPTION_NOT_EMPTY)
    @Size(min = 2, max = 50)
    private String description;

    private String location;

    @NotNull
    private BigDecimal price;

    @JsonFormat(pattern = ValidationMessages.FORMAT_DATE)
    private LocalDateTime startDataTime;
}
