package com.project.exceptionHandler;

import com.project.exceptionHandler.customException.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

    return new ResponseEntity<>(apiError, status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.BAD_REQUEST, ex.getCause().getMessage(), ex.getMessage());

    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler(value = {ModelConflictException.class})
  public ResponseEntity<Object> modelConflictException(RuntimeException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.CONFLICT, ex.getCause().getMessage(), ex.getMessage());

    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler(value = {BadCredentialException.class})
  public ResponseEntity<Object> badCredentialException(RuntimeException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.FORBIDDEN, ex.getCause().getMessage(), ex.getMessage());

    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler(value = {UsernameNotFoundException.class, EmailNotFoundException.class})
  public ResponseEntity<Object> usernameAndEmailNotFoundException(
      RuntimeException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), ex.getMessage());

    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler(value = {UsernameExistException.class, EmailExistException.class})
  public ResponseEntity<Object> usernameAndEmailExistException(
      RuntimeException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex.getMessage());

    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }
}
