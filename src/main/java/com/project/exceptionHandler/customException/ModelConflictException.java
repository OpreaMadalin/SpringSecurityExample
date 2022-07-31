package com.project.exceptionHandler.customException;

public class ModelConflictException extends RuntimeException {

  public ModelConflictException(String errorMessage, Throwable error) {
    super(errorMessage, error);
  }
}
