package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Dessert
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
public class Dessert   {
  @JsonProperty("dessertName")
  private String dessertName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("dietaryProperties")
  @Valid
  private Map<String, Boolean> dietaryProperties = new HashMap<String, Boolean>();

  @JsonProperty("price")
  private BigDecimal price = null;

  public Dessert dessertName(String dessertName) {
    this.dessertName = dessertName;
    return this;
  }

  /**
   * Get dessertName
   * @return dessertName
  **/
  @ApiModelProperty(example = "double chocolate brownies", required = true, value = "")
      @NotNull

    public String getDessertName() {
    return dessertName;
  }

  public void setDessertName(String dessertName) {
    this.dessertName = dessertName;
  }

  public Dessert description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "four large gooey chocolate brownies with chocolate chunks", required = true, value = "")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Dessert dietaryProperties(Map<String, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
    return this;
  }

  public Dessert putDietaryPropertiesItem(String key, Boolean dietaryPropertiesItem) {
    this.dietaryProperties.put(key, dietaryPropertiesItem);
    return this;
  }

  /**
   * Get dietaryProperties
   * @return dietaryProperties
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Map<String, Boolean> getDietaryProperties() {
    return dietaryProperties;
  }

  public void setDietaryProperties(Map<String, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
  }

  public Dessert price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(example = "4.99", required = true, value = "")
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
    Dessert dessert = (Dessert) o;
    return Objects.equals(this.dessertName, dessert.dessertName) &&
        Objects.equals(this.description, dessert.description) &&
        Objects.equals(this.dietaryProperties, dessert.dietaryProperties) &&
        Objects.equals(this.price, dessert.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dessertName, description, dietaryProperties, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Dessert {\n");
    
    sb.append("    dessertName: ").append(toIndentedString(dessertName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
