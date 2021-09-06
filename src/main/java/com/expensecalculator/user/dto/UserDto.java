package com.expensecalculator.user.dto;

import com.expensecalculator.event.dto.EventDto;
import com.expensecalculator.payment.dto.PaymentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

@Builder
@Getter
@Setter
public class UserDto {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private EventDto event;
    private List<PaymentDto> payments;
}
