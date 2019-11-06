package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets DietaryProperty
 */
public enum DietaryProperty {
  VEGETARIAN("Vegetarian"),
  VEGAN("Vegan"),
  GLUTEN_FREE("Gluten Free");

  private String value;

  DietaryProperty(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static DietaryProperty fromValue(String text) {
    for (DietaryProperty b : DietaryProperty.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
