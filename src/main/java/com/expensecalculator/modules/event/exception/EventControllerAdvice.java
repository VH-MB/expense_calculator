package com.expensecalculator.modules.event.exception;

import com.expensecalculator.modules.event.exception.EventNotFoundException;
import com.expensecalculator.modules.user.exeption.UserNotFoundException;
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
class EventControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Object> eventNotFoundHandler(EventNotFoundException eventNotFoundException,
                                                       WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("message", eventNotFoundException.getMessage());

        return handleExceptionInternal(eventNotFoundException,
                body, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND,
                request);
    }
}
