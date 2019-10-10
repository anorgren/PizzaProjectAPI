package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {
  SMALL("Small"),
  MEDIUM("Medium"),
  LARGE("Large");

  private String value;

  Size(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Size fromValue(String text) {
    for (Size size : Size.values()) {
      if (String.valueOf((size.value)).equals(text)) {
        return size;
      }
    }
    return null;
  }
}
