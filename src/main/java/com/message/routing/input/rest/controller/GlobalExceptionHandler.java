package com.message.routing.input.rest.controller;

import com.message.routing.domain.exception.UserNotFoundException;
import com.message.routing.input.rest.dto.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleGlobalException(final RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }
}
