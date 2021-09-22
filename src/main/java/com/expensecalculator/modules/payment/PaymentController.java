package com.expensecalculator.modules.payment;


import com.expensecalculator.modules.payment.dto.PaymentDto;
import com.expensecalculator.security.payload.response.MessageResponse;
import com.expensecalculator.security.validation.ResponseErrorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
@CrossOrigin(origins = "http://localhost:4200")
class PaymentController {

    private final PaymentService paymentService;
    private final PaymentFacade paymentFacade;
    private final ResponseErrorValidation responseErrorValidation;

    @GetMapping("/all")
    ResponseEntity<List<PaymentDto>> getAllPayment() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAllPayment()
                        .stream().map(paymentFacade::paymentToPaymentDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/find/{paymentId}")
    ResponseEntity<PaymentDto> getPaymentById(@PathVariable("paymentId") String paymentId) {
        Payment payment = paymentService.findPaymentById(Long.parseLong(paymentId));
        return ResponseEntity.status(HttpStatus.OK).body(paymentFacade.paymentToPaymentDto(payment));
    }

    @PostMapping("/add/{userId}")
    ResponseEntity<Object> createPayment(@Valid @RequestBody PaymentDto paymentDto,
                                         @PathVariable("userId") String userId,
                                         BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Payment payment = paymentService.createPayment(paymentDto, Long.parseLong(userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentFacade.paymentToPaymentDto(payment));
    }

    @DeleteMapping("/remove/{paymentId}")
    ResponseEntity<MessageResponse> deletePaymentById(@PathVariable("paymentId") String paymentId) {
        paymentService.deletePaymentById(Long.parseLong(paymentId));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Payment was deleted"));
    }
}
