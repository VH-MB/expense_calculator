package com.expensecalculator.modules.user.dto;

import com.expensecalculator.modules.event.Event;
import com.expensecalculator.modules.event.dto.EventDto;
import com.expensecalculator.modules.payment.dto.PaymentDto;
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
    private List<PaymentDto> payments;
}
