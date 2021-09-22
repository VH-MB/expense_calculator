package com.expensecalculator.modules.payment;

import com.expensecalculator.modules.payment.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
class PaymentFacade {
    public PaymentDto paymentToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getIdPayment())
                .description(payment.getDescription())
                .location(payment.getLocation())
                .price(payment.getPrice())
                .startDataTime(payment.getStartDataTime())
                .build();
    }
}
