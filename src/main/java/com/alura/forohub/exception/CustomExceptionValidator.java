package com.alura.forohub.exception;

public class CustomExceptionValidator extends RuntimeException {
  /**
   * Customized exception message.
   *
   * @param msg The error message that explains the reason for the exception.
   */
  public CustomExceptionValidator(String msg) {
    super(msg);
  }
}
