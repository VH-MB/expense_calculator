package com.expensecalculator.modules.payment.exception;


public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(Long paymentId) {
            super(String.format("The payment with ID %d was not found", paymentId));
        }
    }
