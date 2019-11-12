package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.BreadstickRepository;

/**
 * Breadstick
 */
@Document(collection = "Breadsticks")
@Validated
@JsonTypeName("Breadstick")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
public class Breadstick extends Item  {
  private static final BigDecimal SMALL_PRICE = new BigDecimal(2.99);
  private static final BigDecimal LARGE_PRICE = new BigDecimal(4.99);
  private static final BigDecimal WITH_CHEESE_PRICE = new BigDecimal(2.00);

  public static void initialize(BreadstickRepository repository) {
    if (repository.count() > 0) {
      return;
    }
    DataConfiguration.backfillBreadsticksRepository(repository);
  }

  /**
   * Gets or Sets size
   */
  public enum SizeEnum {
    SMALL("small"),
    LARGE("large");

    private String value;

    SizeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SizeEnum fromValue(String text) {
      for (SizeEnum b : SizeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("size")
  private SizeEnum size = null;

  @JsonProperty("withCheese")
  private Boolean withCheese = null;

  @JsonProperty("dietaryProperties")
  @Valid
  private Map<DietaryProperty, Boolean> dietaryProperties = new HashMap<DietaryProperty, Boolean>();

  @JsonProperty("price")
  private BigDecimal price = new BigDecimal(0);

  public Breadstick size(SizeEnum size) {
    this.size = size;
    switch (this.size) {
      case SMALL:
        this.price = this.price.add(SMALL_PRICE);
        break;
      case LARGE:
        this.price = this.price.add(LARGE_PRICE);
        break;
    }
    return this;
  }

  /**
   * Get size
   * @return size
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public SizeEnum getSize() {
    return size;
  }

  public Breadstick withCheese(Boolean withCheese) {
    this.withCheese = withCheese;
    if (this.withCheese) {
      this.price = this.price.add(WITH_CHEESE_PRICE);
    }
    return this;
  }

  /**
   * Get withCheese
   * @return withCheese
  **/
  @ApiModelProperty(required = true, value = "true/false")
  @NotNull
  public Boolean isWithCheese() {
    return withCheese;
  }

  public Breadstick dietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
    return this;
  }

  public Breadstick putDietaryPropertiesItem(DietaryProperty key, Boolean dietaryPropertiesItem) {
    this.dietaryProperties.put(key, dietaryPropertiesItem);
    return this;
  }

  /**
   * Get dietaryProperties
   * @return dietaryProperties
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Map<DietaryProperty, Boolean> getDietaryProperties() {
    return dietaryProperties;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(example = "3.99", required = true, value = "")
  @NotNull
  @Override
  @Valid
  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Breadstick breadstick = (Breadstick) o;
    return Objects.equals(this.size, breadstick.size) &&
        Objects.equals(this.withCheese, breadstick.withCheese) &&
        Objects.equals(this.dietaryProperties, breadstick.dietaryProperties) &&
        Objects.equals(this.price, breadstick.price) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(size, withCheese, dietaryProperties, price, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Breadstick {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    withCheese: ").append(toIndentedString(withCheese)).append("\n");
    sb.append("    dietaryProperties: ").append(toIndentedString(dietaryProperties)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
