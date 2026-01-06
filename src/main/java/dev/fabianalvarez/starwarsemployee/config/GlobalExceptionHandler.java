package dev.fabianalvarez.starwarsemployee.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.fabianalvarez.starwarsemployee.dto.ErrorResponse;
import dev.fabianalvarez.starwarsemployee.exception.EmployeeNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Global exception handler for the application.
 * Provides consistent error responses across all REST endpoints.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle EmployeeNotFoundException (404 Not Found).
   */
  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEmployeeNotFound(
      EmployeeNotFoundException ex,
      HttpServletRequest request
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage(),
        request.getRequestURI(),
        LocalDateTime.now(),
        null
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  /**
   * Handle validation errors (400 Bad Request).
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException ex,
      HttpServletRequest request
  ) {
    List<ErrorResponse.FieldError> fieldErrors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> new ErrorResponse.FieldError(
            error.getField(),
            error.getDefaultMessage()
        ))
        .toList();

    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Validation failed",
        request.getRequestURI(),
        LocalDateTime.now(),
        fieldErrors
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  /**
   * Handle JSON parsing errors (400 Bad Request).
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpServletRequest request
  ) {
    String message = "Invalid JSON format or data type";
    if (ex.getCause() != null) {
      String cause = ex.getCause().getMessage();
      if (cause != null && cause.contains("LocalDate")) {
        message = "Invalid date format. Expected: yyyy-MM-dd";
      }
    }

    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        message,
        request.getRequestURI(),
        LocalDateTime.now(),
        null
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  /**
   * Handle generic exceptions (500 Internal Server Error).
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(
      Exception ex,
      HttpServletRequest request
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "An unexpected error occurred",
        request.getRequestURI(),
        LocalDateTime.now(),
        null
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
