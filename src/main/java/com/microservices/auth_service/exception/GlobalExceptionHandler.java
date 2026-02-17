package com.microservices.auth_service.exception;

import com.microservices.auth_service.dto.errorresponse.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<?> handleAuthServiceException(AuthServiceException exception) {
        ErrorDto errorDto = new ErrorDto();
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        errorDto.setErrorMessages(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        errorDto.setErrorMessages(errors);
        return ResponseEntity.badRequest().body(errorDto);
    }
}
