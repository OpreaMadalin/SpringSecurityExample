package com.project.exceptionHandler.customException;

public class UsernameExistException extends RuntimeException {

  public UsernameExistException(String message) {
    super(message);
  }
}
