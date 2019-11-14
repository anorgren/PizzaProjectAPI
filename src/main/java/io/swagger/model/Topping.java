package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.ToppingRepository;

@Document(collection = "Toppings")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-30T04:43:18.635Z[GMT]")
public class Topping implements ToppingRepository.ToppingName{

  public static void initialize(ToppingRepository repository) {
    if (repository.count() > 0) {
      return;
    }
    DataConfiguration.backfillToppingsRepository(repository);
  }

  @JsonProperty("toppingName")
  private String toppingName = null;

  @JsonProperty("dietaryProperties")
  @Valid
  private Map<DietaryProperty, Boolean> dietaryProperties = null;

  @JsonProperty("price")
  private Double price = null;

  public Topping toppingName(String toppingName) {
    this.toppingName = toppingName;
    return this;
  }

  @ApiModelProperty(example = "tomato", required = true, value = "")
  @NotNull
  public String getToppingName() {
    return toppingName;
  }

  public void setToppingName(String toppingName) {
    this.toppingName = toppingName;
  }

  public Topping dietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
    return this;
  }

  public Topping putDietaryPropertiesItem(DietaryProperty key, Boolean dietaryPropertiesItem) {
    if (this.dietaryProperties == null) {
      this.dietaryProperties = new HashMap<DietaryProperty, Boolean>();
    }
    this.dietaryProperties.put(key, dietaryPropertiesItem);
    return this;
  }

  /**
   * Get dietaryProperties
   *
   * @return dietaryProperties
   **/
  @ApiModelProperty(value = "")

  public Map<DietaryProperty, Boolean> getDietaryProperties() {
    return dietaryProperties;
  }

  public void setDietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
  }

  public Topping price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   *
   * @return price
   **/
  @ApiModelProperty(example = "1", value = "")

  @Valid
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
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
        Objects.equals(this.dietaryProperties, topping.dietaryProperties) &&
        Objects.equals(this.price, topping.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toppingName, dietaryProperties, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Topping {\n");

    sb.append("    toppingName: ").append(toIndentedString(toppingName)).append("\n");
    sb.append("    dietaryProperties: ").append(toIndentedString(dietaryProperties)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
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
}
