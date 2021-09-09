package com.expensecalculator.event.exception;

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
public class EventRestControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Object> eventNotFoundHandler(EventNotFoundException eventException, WebRequest request) {

        Map<String, Object> body;
        body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", eventException.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());

        return handleExceptionInternal(eventException, body, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND,
                request);
    }
}
