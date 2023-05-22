package com.weather.prediction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> cityNotFoundException(CityNotFoundException ex, WebRequest request) {
        return responseBuilder(ex.getMessage());
    }

    @ExceptionHandler(CountFoundException.class)
    public ResponseEntity<Object> countFoundException(CountFoundException ex, WebRequest request) {
        return responseBuilder(ex.getMessage());
    }

    private ResponseEntity responseBuilder(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        logger.error(body);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("Ex", ex);
        body.put("message", ex.getMessage());
        logger.error(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
