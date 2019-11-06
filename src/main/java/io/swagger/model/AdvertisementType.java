package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets AdvertisementType
 */
public enum AdvertisementType {
  SPECIAL("Special"),
  COUPON("Coupon");

  private String value;

  AdvertisementType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AdvertisementType fromValue(String text) {
    for (AdvertisementType b : AdvertisementType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
