package com.example.alura.forohub.domain;

public class ExceptionValidator extends RuntimeException {
  /**
   * Customized exception message.
   *
   * @param msg The error message that explains the reason for the exception.
   */
  public ExceptionValidator(String msg) {
    super(msg);
  }
}
