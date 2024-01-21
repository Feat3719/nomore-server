package com.kimoi.nomore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kimoi.nomore.dto.exception.ErrorMessageResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserIdNotFound(String errorMessage) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageResponse(errorMessage));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handle(String errorMessage) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageResponse(errorMessage));
    }
    
}
