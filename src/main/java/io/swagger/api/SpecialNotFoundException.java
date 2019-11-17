package io.swagger.api;

public class SpecialNotFoundException extends RuntimeException {

  private static final String NOT_IMPLEMENTED_MSG = "Special not implemented: ";

  public SpecialNotFoundException(String specialId) {
    super(NOT_IMPLEMENTED_MSG + specialId);
  }
}
