package io.swagger.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Crust;
import io.swagger.model.PizzaSize;
import io.swagger.model.Sauce;
import io.swagger.model.Topping;
import io.swagger.model.DietaryProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Pizza
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T22:37:07.679Z[GMT]")
public class Pizza   {
  @JsonProperty("pizzaName")
  private String pizzaName = null;

  @JsonProperty("size")
  private PizzaSize size = null;

  @JsonProperty("sauce")
  private Sauce sauce = null;

  @JsonProperty("crust")
  private Crust crust = null;

  @JsonProperty("dietaryProperties")
  private Map<DietaryProperty, Boolean> dietaryProperties = new HashMap<>();

  @JsonProperty("toppings")
  @Valid
  private List<Topping> toppings = new ArrayList<Topping>();

  public Pizza pizzaName(String pizzaName) {
    this.pizzaName = pizzaName;
    this.dietaryProperties.put(DietaryProperty.VEGAN, true);
    this.dietaryProperties.put(DietaryProperty.VEGETARIAN, true);
    this.dietaryProperties.put(DietaryProperty.GLUTEN_FREE, true);
    return this;
  }

  /**
   * Get pizzaName
   * @return pizzaName
  **/
  @ApiModelProperty(example = "meat lovers", required = true, value = "")
      @NotNull

    public String getPizzaName() {
    return pizzaName;
  }

  public void setPizzaName(String pizzaName) {
    this.pizzaName = pizzaName;
  }

  public Pizza size(PizzaSize size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public PizzaSize getSize() {
    return size;
  }

  public Pizza sauce(Sauce sauce) {
    this.sauce = sauce;
    checkSauceDietaryProperties(sauce);
    return this;
  }
  private void checkSauceDietaryProperties(Sauce sauce) {
    for(Map.Entry<DietaryProperty, Boolean> entry : sauce.getDietaryProperties().entrySet()) {
      if (entry.getValue().equals(false)) {
        dietaryProperties.put(entry.getKey(), false);
      }
    }
  }

  /**
   * Get sauce
   * @return sauce
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Sauce getSauce() {
    return sauce;
  }

  public void setSauce(Sauce sauce) {
    this.sauce = sauce;
  }

  public Pizza crust(Crust crust) {
    this.crust = crust;
    return this;
  }

  /**
   * Get crust
   * @return crust
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Crust getCrust() {
    return crust;
  }

  public void setCrust(Crust crust) {
    this.crust = crust;
  }

  public Pizza toppings(List<Topping> toppings) {
    this.toppings = toppings;
    return this;
  }

  public Pizza addToppingsItem(Topping toppingsItem) {
    this.toppings.add(toppingsItem);
    return this;
  }

  /**
   * Get toppings
   * @return toppings
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<Topping> getToppings() {
    return toppings;
  }

  public void setToppings(List<Topping> toppings) {
    this.toppings = toppings;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pizza pizza = (Pizza) o;
    return Objects.equals(this.pizzaName, pizza.pizzaName) &&
        Objects.equals(this.size, pizza.size) &&
        Objects.equals(this.sauce, pizza.sauce) &&
        Objects.equals(this.crust, pizza.crust) &&
        Objects.equals(this.toppings, pizza.toppings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pizzaName, size, sauce, crust, toppings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pizza {\n");
    
    sb.append("    pizzaName: ").append(toIndentedString(pizzaName)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    sauce: ").append(toIndentedString(sauce)).append("\n");
    sb.append("    crust: ").append(toIndentedString(crust)).append("\n");
    sb.append("    toppings: ").append(toIndentedString(toppings)).append("\n");
    sb.append("    dietaryProperties: ").append(toIndentedString(dietaryProperties)).append("\n");
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
