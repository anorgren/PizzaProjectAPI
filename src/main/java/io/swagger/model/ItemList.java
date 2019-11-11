package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;

/**
 * ItemList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:33:40.208Z[GMT]")
public class ItemList {
  @JsonProperty("orderItems")
  @Valid
  private List<Item> orderItems = null;

  public ItemList orderItems(List<Item> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public ItemList addOrderItemsItem(Item orderItemsItem) {
    if (this.orderItems == null) {
      this.orderItems = new ArrayList<Item>();
    }
    this.orderItems.add(orderItemsItem);
    return this;
  }

  /**
   * Get orderItems
   * @return orderItems
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Item> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<Item> orderItems) {
    this.orderItems = orderItems;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemList itemList = (ItemList) o;
    return Objects.equals(this.orderItems, itemList.orderItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemList {\n");
    
    sb.append("    orderItems: ").append(toIndentedString(orderItems)).append("\n");
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
