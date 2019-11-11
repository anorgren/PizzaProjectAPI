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
import io.swagger.repository.SodaRepository;

/**
 * Soda
 */
@Document(collection = "Sodas")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:33:40.208Z[GMT]")
@JsonTypeName("Soda")
public class Soda extends Item  {

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
    SIX_PACK("SIX_PACK"),
    
    TWO_LITER("TWO_LITER"),
    
    TWENTY_OUNCE_BOTTLE("TWENTY_OUNCE_BOTTLE");

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
  private BigDecimal price = null;

  public Soda sodaName(String sodaName) {
    this.sodaName = sodaName;
    return this;
  }

  /**
   * Get sodaName
   * @return sodaName
  **/
  @ApiModelProperty(example = "sprite", required = true, value = "")
      @NotNull

    public String getSodaName() {
    return sodaName;
  }

  public void setSodaName(String sodaName) {
    this.sodaName = sodaName;
  }

  public Soda size(SizeEnum size) {
    this.size = size;
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

  public void setSize(SizeEnum size) {
    this.size = size;
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
   * @return dietaryProperties
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Map<DietaryProperty, Boolean> getDietaryProperties() {
    return dietaryProperties;
  }

  public void setDietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
  }

  public Soda price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(example = "1.99", required = true, value = "")
      @NotNull
    @Override
    @Valid
    public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
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
        Objects.equals(this.price, soda.price) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sodaName, size, dietaryProperties, price, super.hashCode());
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
