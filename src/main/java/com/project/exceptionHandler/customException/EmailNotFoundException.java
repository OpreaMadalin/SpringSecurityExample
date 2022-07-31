package com.project.exceptionHandler.customException;

public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException(String message) {
    super(message);
  }
}
