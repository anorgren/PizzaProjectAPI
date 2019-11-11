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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:33:40.208Z[GMT]")
@JsonTypeName("Breadstick")
public class Breadstick extends Item  {
  @JsonProperty("name")
  private String name = null;

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
    SMALL("SMALL"),
    
    LARGE("LARGE");

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
  private BigDecimal price = null;

  public Breadstick name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "garlic breadsticks", value = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Breadstick size(SizeEnum size) {
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

  public Breadstick withCheese(Boolean withCheese) {
    this.withCheese = withCheese;
    return this;
  }

  /**
   * Get withCheese
   * @return withCheese
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Boolean isWithCheese() {
    return withCheese;
  }

  public void setWithCheese(Boolean withCheese) {
    this.withCheese = withCheese;
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

  public void setDietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
  }

  public Breadstick price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(example = "3.99", required = true, value = "")
      @NotNull

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
    Breadstick breadstick = (Breadstick) o;
    return Objects.equals(this.name, breadstick.name) &&
        Objects.equals(this.size, breadstick.size) &&
        Objects.equals(this.withCheese, breadstick.withCheese) &&
        Objects.equals(this.dietaryProperties, breadstick.dietaryProperties) &&
        Objects.equals(this.price, breadstick.price) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, size, withCheese, dietaryProperties, price, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Breadstick {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
