package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets AdvertisementType
 */
public enum AdvertisementType {
  SPECIAL("Special"),
    CUPON("Cupon");

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
