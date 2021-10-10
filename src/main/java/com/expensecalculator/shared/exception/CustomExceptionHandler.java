package com.expensecalculator.shared.exception;

import com.expensecalculator.modules.event.exception.EventNotFoundException;
import com.expensecalculator.modules.payment.exception.PaymentNotFoundException;
import com.expensecalculator.modules.person.exeption.PersonNotFoundException;
import com.expensecalculator.shared.validation.ValidationErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Component
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * EXCEPTION
     */

    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<Object> eventNotFoundException(EventNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<Object> personNotFoundException(PersonNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PaymentNotFoundException.class)
    public ResponseEntity<Object> paymentNotFoundException(PaymentNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    /**
     * VALIDATION ERROR
     */

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Get the error messages for invalid fields
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));

        // Create ValidationErrorResponse object using error messages and request details
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(errorMessage, request.getDescription(false));

        return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
