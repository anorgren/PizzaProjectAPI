package io.swagger.model;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * Item
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
@JsonTypeInfo(use = NAME, include = PROPERTY, property = "itemType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Pizza.class, name = "Pizza"),
        @JsonSubTypes.Type(value = Dessert.class, name = "Dessert"),
        @JsonSubTypes.Type(value = Soda.class, name = "Soda"),
        @JsonSubTypes.Type(value = Breadstick.class, name = "Breadstick")
})
public class Item {
  @JsonProperty("itemType")
  private String itemType = null;

  public Item itemType(String itemType) {
    this.itemType = itemType;
    return this;
  }

  /**
   * Get itemType
   *
   * @return itemType
   **/
  @ApiModelProperty(example = "Item", required = true, value = "")
  @NotNull

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  //must be overridden by subtypes
  public Double getPrice() {
    return null;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.itemType, item.itemType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");

    sb.append("    itemType: ").append(toIndentedString(itemType)).append("\n");
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
