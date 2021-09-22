package com.expensecalculator.modules.payment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {

    @Id
    private Long id;

    @NotBlank
    @Size(min = 5, max = 50)
    private String description;

    private String location;

    @NotNull
    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime startDataTime;
}
