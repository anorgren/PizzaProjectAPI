package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

/**
 * Receipt
 */
@Document(collection = "Receipts")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-16T17:51:54.198Z[GMT]")
public class Receipt   {
  @JsonProperty("receiptId")
  private String receiptId = null;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("storeId")
  private String storeId = null;

  @JsonProperty("orderDateTime")
  private OffsetDateTime orderDateTime = null;

  @JsonProperty("orderAmount")
  private Price orderAmount = null;

  @JsonProperty("itemList")
  @Valid
  private List<Item> itemList = null;

  public Receipt receiptId(String receiptId) {
    this.receiptId = receiptId;
    return this;
  }

  /**
   * Get receiptId
   * @return receiptId
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
      @NotNull

    public String getReceiptId() {
    return receiptId;
  }

  public void setReceiptId(String receiptId) {
    this.receiptId = receiptId;
  }

  public Receipt orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
      @NotNull

    public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Receipt storeId(String storeId) {
    this.storeId = storeId;
    return this;
  }

  /**
   * Get storeId
   * @return storeId
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
      @NotNull

    public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public Receipt orderDateTime(OffsetDateTime orderDateTime) {
    this.orderDateTime = orderDateTime;
    return this;
  }

  /**
   * Get orderDateTime
   * @return orderDateTime
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public OffsetDateTime getOrderDateTime() {
    return orderDateTime;
  }

  public void setOrderDateTime(OffsetDateTime orderDateTime) {
    this.orderDateTime = orderDateTime;
  }

  public Receipt orderAmount(Price orderAmount) {
    this.orderAmount = orderAmount;
    return this;
  }

  /**
   * Get orderAmount
   * @return orderAmount
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Price getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(Price orderAmount) {
    this.orderAmount = orderAmount;
  }

  public Receipt itemList(List<Item> itemList) {
    this.itemList = itemList;
    return this;
  }

  public Receipt addItemListItem(Item itemListItem) {
    if (this.itemList == null) {
      this.itemList = new ArrayList<Item>();
    }
    this.itemList.add(itemListItem);
    return this;
  }

  /**
   * Get itemList
   * @return itemList
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Receipt receipt = (Receipt) o;
    return Objects.equals(this.receiptId, receipt.receiptId) &&
        Objects.equals(this.orderId, receipt.orderId) &&
        Objects.equals(this.storeId, receipt.storeId) &&
        Objects.equals(this.orderDateTime, receipt.orderDateTime) &&
        Objects.equals(this.orderAmount, receipt.orderAmount) &&
        Objects.equals(this.itemList, receipt.itemList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(receiptId, orderId, storeId, orderDateTime, orderAmount, itemList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Receipt {\n");
    
    sb.append("    receiptId: ").append(toIndentedString(receiptId)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    storeId: ").append(toIndentedString(storeId)).append("\n");
    sb.append("    orderDateTime: ").append(toIndentedString(orderDateTime)).append("\n");
    sb.append("    orderAmount: ").append(toIndentedString(orderAmount)).append("\n");
    sb.append("    itemList: ").append(toIndentedString(itemList)).append("\n");
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
