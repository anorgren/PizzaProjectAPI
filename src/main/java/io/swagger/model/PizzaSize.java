package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets PizzaSize
 */
public enum PizzaSize {
  SMALL("10 inches"),
  MEDIUM("12 inches"),
  LARGE("14 inches");

  private String value;

  PizzaSize(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PizzaSize fromValue(String text) {
    for (PizzaSize b : PizzaSize.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public String getValue() {
    return value;
  }
}
