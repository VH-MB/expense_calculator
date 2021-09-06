package com.expensecalculator.payment.dto;

import com.expensecalculator.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PaymentDto {

    @Id
    private Long id;

    private String description;

    private String location;

    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime startDataTime;

    public UserDto users;
}
