package com.project.exceptionHandler.customException;

public class BadCredentialException extends RuntimeException {

  public BadCredentialException(String errorMessage, Throwable error) {
    super(errorMessage, error);
  }
}
