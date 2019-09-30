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
 * Topping
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-30T04:43:18.635Z[GMT]")
public class Topping   {
  @JsonProperty("toppingName")
  private String toppingName = null;

  @JsonProperty("dietryProperties")
  @Valid
  private Map<String, Boolean> dietryProperties = null;

  @JsonProperty("price")
  private BigDecimal price = null;

  public Topping toppingName(String toppingName) {
    this.toppingName = toppingName;
    return this;
  }

  /**
   * Get toppingName
   * @return toppingName
  **/
  @ApiModelProperty(example = "Tomato", required = true, value = "")
  @NotNull

  public String getToppingName() {
    return toppingName;
  }

  public void setToppingName(String toppingName) {
    this.toppingName = toppingName;
  }

  public Topping dietryProperties(Map<String, Boolean> dietryProperties) {
    this.dietryProperties = dietryProperties;
    return this;
  }

  public Topping putDietryPropertiesItem(String key, Boolean dietryPropertiesItem) {
    if (this.dietryProperties == null) {
      this.dietryProperties = new HashMap<String, Boolean>();
    }
    this.dietryProperties.put(key, dietryPropertiesItem);
    return this;
  }

  /**
   * Get dietryProperties
   * @return dietryProperties
  **/
  @ApiModelProperty(value = "")

  public Map<String, Boolean> getDietryProperties() {
    return dietryProperties;
  }

  public void setDietryProperties(Map<String, Boolean> dietryProperties) {
    this.dietryProperties = dietryProperties;
  }

  public Topping price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(example = "1", value = "")

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
    Topping topping = (Topping) o;
    return Objects.equals(this.toppingName, topping.toppingName) &&
        Objects.equals(this.dietryProperties, topping.dietryProperties) &&
        Objects.equals(this.price, topping.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toppingName, dietryProperties, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Topping {\n");
    
    sb.append("    toppingName: ").append(toIndentedString(toppingName)).append("\n");
    sb.append("    dietryProperties: ").append(toIndentedString(dietryProperties)).append("\n");
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
