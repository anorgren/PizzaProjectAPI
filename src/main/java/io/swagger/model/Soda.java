package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.SodaRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * Soda
 */
@Document(collection = "Sodas")
@Validated
@JsonTypeName("Soda")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
public class Soda extends Item {

  private static final Double TWO_LITER_PRICE = new Double(2.59);
  private static final Double TWENTY_OUNCE_PRICE = new Double(1.75);
  private static final Double SIX_PACK_PRICE = new Double(3.49);
  private static final String ITEM_TYPE = "Soda";

  public static void initialize(SodaRepository repository) {
    if (repository.count() > 0) {
      return;
    }
    DataConfiguration.backfillSodaRepository(repository);
  }

  @JsonProperty("sodaName")
  private String sodaName = null;

  /**
   * Gets or Sets size
   */
  public enum SizeEnum {
    SIX_PACK("six pack"),

    TWO_LITER("two liter"),

    TWENTY_OUNCE_BOTTLE("twenty ounce bottle");

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

  @JsonProperty("dietaryProperties")
  @Valid
  private Map<DietaryProperty, Boolean> dietaryProperties = new HashMap<DietaryProperty, Boolean>();

  @JsonProperty("price")
  private Double price = new Double(0);

  public Soda sodaName(String sodaName) {
    this.sodaName = sodaName;
    return this;
  }

  /**
   * Get sodaName
   *
   * @return sodaName
   **/
  @ApiModelProperty(example = "sprite", required = true, value = "")
  @NotNull

  public String getSodaName() {
    return sodaName;
  }


  public Soda size(SizeEnum size) {
    this.size = size;
    switch (this.size) {
      case TWO_LITER:
        this.price = TWO_LITER_PRICE;
        break;
      case TWENTY_OUNCE_BOTTLE:
        this.price = TWENTY_OUNCE_PRICE;
        break;
      case SIX_PACK:
        this.price = SIX_PACK_PRICE;
        break;
    }
    return this;

  }

  /**
   * Get size
   *
   * @return size
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public SizeEnum getSize() {
    return size;
  }

  public Soda dietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
    return this;
  }

  public Soda putDietaryPropertiesItem(DietaryProperty key, Boolean dietaryPropertiesItem) {
    this.dietaryProperties.put(key, dietaryPropertiesItem);
    return this;
  }

  /**
   * Get dietaryProperties
   *
   * @return dietaryProperties
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Map<DietaryProperty, Boolean> getDietaryProperties() {
    return dietaryProperties;
  }

  /**
   * Get price
   *
   * @return price
   **/
  @ApiModelProperty(example = "1.99", required = true, value = "")
  @NotNull
  @Override
  @Valid
  public Double getPrice() {
    return price;
  }

  @Override
  public String getItemType() {
    return ITEM_TYPE;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Soda soda = (Soda) o;
    return Objects.equals(this.sodaName, soda.sodaName) &&
            Objects.equals(this.size, soda.size) &&
            Objects.equals(this.dietaryProperties, soda.dietaryProperties) &&
            Objects.equals(this.price, soda.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sodaName, size, dietaryProperties, price);
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Soda {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    sodaName: ").append(toIndentedString(sodaName)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    dietaryProperties: ").append(toIndentedString(dietaryProperties)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
