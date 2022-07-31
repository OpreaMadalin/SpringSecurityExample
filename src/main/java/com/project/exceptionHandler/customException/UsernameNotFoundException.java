package com.project.exceptionHandler.customException;

public class UsernameNotFoundException extends RuntimeException {

  public UsernameNotFoundException(String message) {
    super(message);
  }
}
