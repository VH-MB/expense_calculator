package com.expensecalculator.modules.payment;

import com.expensecalculator.modules.payment.dto.PaymentDto;
import com.expensecalculator.modules.payment.exception.PaymentNotFoundException;
import com.expensecalculator.modules.person.Person;
import com.expensecalculator.modules.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger LOG = LogManager.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final PaymentQueryRepository paymentQueryRepository;
    private final PersonService personService;

    public List<Payment> findAllPayment() {
        List<Payment> payments = paymentQueryRepository.findAll();
        if (payments.isEmpty()) return Collections.emptyList();
        return payments;
    }

    public Payment findPaymentById(Long paymentId) {
        return paymentQueryRepository.findByIdPayment(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }

    public Payment createPayment(PaymentDto paymentDto, Long personId) {
        Person person = personService.findPersonById(personId);

        Payment payment = new Payment();
        payment.setIdPayment(paymentDto.getId());
        payment.setDescription(paymentDto.getDescription());
        payment.setLocation(paymentDto.getLocation());
        payment.setPrice(paymentDto.getPrice().setScale(2, BigDecimal.ROUND_DOWN));
        payment.addPerson(person);

        LOG.info("Saving payment for Person: {}", person.getId());
        return paymentRepository.save(payment);
    }

    public void deletePaymentById(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
