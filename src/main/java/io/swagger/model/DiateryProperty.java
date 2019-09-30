package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets DiateryProperty
 */
public enum DiateryProperty {
  VEGETATIAN("Vegetatian"),
    VEGAN("Vegan"),
    GLUTEN_FREE("Gluten Free");

  private String value;

  DiateryProperty(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static DiateryProperty fromValue(String text) {
    for (DiateryProperty b : DiateryProperty.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
