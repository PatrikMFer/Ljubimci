package com.ferovac.backend.Exception;

import com.ferovac.backend.dto.ApiResponseWrapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponseWrapper<?>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiResponseWrapper<Void> errorResponse = new ApiResponseWrapper<>("NOT FOUND", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ElementCreationException.class)
    public ResponseEntity<ApiResponseWrapper<?>> handleElementCreationException(ElementCreationException ex) {
        ApiResponseWrapper<Void> errorResponse = new ApiResponseWrapper<>("BAD REQUEST", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseWrapper<?>> handleOtherExceptions(Exception ex) {
        ApiResponseWrapper<Void> errorResponse = new ApiResponseWrapper<>("INTERNAL SERVER ERROR", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
