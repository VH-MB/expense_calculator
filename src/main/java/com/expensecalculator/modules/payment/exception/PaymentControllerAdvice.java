package com.expensecalculator.modules.payment.exception;

import com.expensecalculator.modules.event.exception.EventNotFoundException;
import com.expensecalculator.modules.payment.exception.PaymentNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
class PaymentControllerAdvice extends  ResponseEntityExceptionHandler{

        @ExceptionHandler(PaymentNotFoundException.class)
        public ResponseEntity<Object> paymentNotFoundHandler(PaymentNotFoundException paymentNotFoundException,
                                                             WebRequest request) {

            Map<String, Object> bodyPayment = new LinkedHashMap<>();

            bodyPayment.put("timestamp", LocalDateTime.now());
            bodyPayment.put("status", HttpStatus.NOT_FOUND.value());
            bodyPayment.put("message", paymentNotFoundException.getMessage());

            return handleExceptionInternal(paymentNotFoundException,
                    bodyPayment, HttpHeaders.EMPTY,
                    HttpStatus.NOT_FOUND, request);
        }
    }

