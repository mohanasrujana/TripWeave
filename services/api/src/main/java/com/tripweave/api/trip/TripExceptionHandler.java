package com.tripweave.api.trip;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class TripExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception) {
    var fieldErrors = new LinkedHashMap<String, String>();

    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(error -> fieldErrors.putIfAbsent(error.getField(), error.getDefaultMessage()));

    return ResponseEntity.badRequest().body(new ApiError("Validation failed", fieldErrors));
  }

  @ExceptionHandler(InvalidTimeZoneException.class)
  ResponseEntity<ApiError> handleInvalidTimeZone(InvalidTimeZoneException exception) {
    return ResponseEntity.badRequest()
        .body(new ApiError(exception.getMessage(), Map.of("timeZone", exception.getMessage())));
  }

  @ExceptionHandler(TripNotFoundException.class)
  ResponseEntity<ApiError> handleNotFound(TripNotFoundException exception) {
    return ResponseEntity.status(404).body(new ApiError(exception.getMessage(), Map.of()));
  }
}
