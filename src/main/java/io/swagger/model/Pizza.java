package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.PizzaRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * Pizza
 */
@Validated
@JsonTypeName("Pizza")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T21:13:59.483Z[GMT]")
public class Pizza extends Item {

  private final Double SMALL_BASE_PRICE = 8.00;
  private final Double MEDIUM_BASE_PRICE = 10.00;
  private final Double LARGE_BASE_PRICE = 12.00;
  private static final String ITEM_TYPE = "Pizza";

  public static void initialize(PizzaRepository repository) {
    if (repository.count() > 0) {
      return;
    }
    DataConfiguration.backfillPizzaRepository(repository);
  }

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

  @JsonProperty("price")
  private Double price = null;

  public Pizza pizzaName(String pizzaName) {
    this.pizzaName = pizzaName;
    this.dietaryProperties.put(DietaryProperty.VEGAN, true);
    this.dietaryProperties.put(DietaryProperty.VEGETARIAN, true);
    this.dietaryProperties.put(DietaryProperty.GLUTEN_FREE, true);
    return this;
  }

  /**
   * Get pizzaName
   *
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
   *
   * @return size
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public PizzaSize getSize() {
    return size;
  }

  public void setSize(PizzaSize size) {
    this.size = size;
  }

  public Pizza sauce(Sauce sauce) {
    this.sauce = sauce;
    checkSauceDietaryProperties(sauce);
    return this;
  }

  private void checkSauceDietaryProperties(Sauce sauce) {
    for (Map.Entry<DietaryProperty, Boolean> entry : sauce.getDietaryProperties().entrySet()) {
      if (entry.getValue().equals(false)) {
        dietaryProperties.put(entry.getKey(), false);
      }
    }
  }

  /**
   * Get sauce
   *
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
   *
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
   *
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

  public Pizza price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   *
   * @return price
   **/
  @ApiModelProperty(example = "12.99", value = "")
  @Override
  @Valid
  public Double getPrice() {
    Double sum = getBasePrice();
    sum += this.getToppings().stream().mapToDouble(Topping::getPrice).sum();
    sum += this.getCrust().getPrice();
    return sum;
  }

  public void setPrice(Double price) {
    this.price = price;
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
    Pizza pizza = (Pizza) o;
    return Objects.equals(this.pizzaName, pizza.pizzaName) &&
        Objects.equals(this.size, pizza.size) &&
        Objects.equals(this.sauce, pizza.sauce) &&
        Objects.equals(this.crust, pizza.crust) &&
        Objects.equals(this.toppings, pizza.toppings) &&
        Objects.equals(this.price, pizza.price) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pizzaName, size, sauce, crust, toppings, price, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pizza {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    pizzaName: ").append(toIndentedString(pizzaName)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    sauce: ").append(toIndentedString(sauce)).append("\n");
    sb.append("    crust: ").append(toIndentedString(crust)).append("\n");
    sb.append("    toppings: ").append(toIndentedString(toppings)).append("\n");
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

  private Double getBasePrice() {  //  private Integer getBasePrice(String size) {
    Double price = 0.0;  //    Integer price = 0;
    switch (this.size.getSizeDescription()) {  //    switch (pizzaSize) {
      case "small":  //      case SMALL:
        price = SMALL_BASE_PRICE;  //        price = SMALL_BASE_PRICE;
        break;  //        break;
      case "medium":  //      case MEDIUM:
        price = MEDIUM_BASE_PRICE;  //        price = MEDIUM_BASE_PRICE;
        break;  //        break;
      case "large":  //      case LARGE:
        price = LARGE_BASE_PRICE;  //        price = LARGE_BASE_PRICE;
        break;  //        break;
    }  //    }
    return price;  //    return price;
  }
}
