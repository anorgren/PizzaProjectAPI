package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Order
 */
@Document(collection = "Orders")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T18:52:07.371Z[GMT]")
public class Order {

    @JsonIgnore
    private String id = null;

    @JsonProperty("orderId")
    private String orderId = null;

    @JsonProperty("storeId")
    private String storeId = null;

    @JsonProperty("itemList")
    @Valid
    private List<Item> itemList = null;

    @JsonProperty("tentativeAmount")
    private Price tentativeAmount = null;

    @JsonProperty("calculatedAmount")
    private Price calculatedAmount = null;

    @JsonProperty("discountAmount")
    private Price discountAmount = null;

    @JsonProperty("payementInformation")
    private PaymentInformation payementInformation = null;

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        CREATED("Created"),

        INPROCESS("InProcess"),

        COMPLETED("Completed");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("status")
    private StatusEnum status = null;

    @JsonProperty("specialId")
    private String specialId = null;

    public Order orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * Get orderId
     *
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

    public Order storeId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    /**
     * Get storeId
     *
     * @return storeId
     **/
    @ApiModelProperty(example = "1", value = "")

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Order itemList(List<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    public Order addItemListItem(Item itemListItem) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<Item>();
        }
        this.itemList.add(itemListItem);
        return this;
    }

    /**
     * Get itemList
     *
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

    public Order tentativeAmount(Price tentativeAmount) {
        this.tentativeAmount = tentativeAmount;
        return this;
    }

    /**
     * Get tentativeAmount
     *
     * @return tentativeAmount
     **/
    @ApiModelProperty(value = "")

    @Valid
    public Price getTentativeAmount() {
        return tentativeAmount;
    }

    public void setTentativeAmount(Price tentativeAmount) {
        this.tentativeAmount = tentativeAmount;
    }

    public Order calculatedAmount(Price calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
        return this;
    }

    /**
     * Get calculatedAmount
     *
     * @return calculatedAmount
     **/
    @ApiModelProperty(value = "")

    @Valid
    public Price getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(Price calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }

    public Order discountAmount(Price discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    /**
     * Get discountAmount
     *
     * @return discountAmount
     **/
    @ApiModelProperty(value = "")

    @Valid
    public Price getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Price discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Order payementInformation(PaymentInformation payementInformation) {
        this.payementInformation = payementInformation;
        return this;
    }

    /**
     * Get payementInformation
     *
     * @return payementInformation
     **/
    @ApiModelProperty(value = "")

    @Valid
    public PaymentInformation getPayementInformation() {
        return payementInformation;
    }

    public void setPayementInformation(PaymentInformation payementInformation) {
        this.payementInformation = payementInformation;
    }

    public Order status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @ApiModelProperty(value = "")

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Order specialId(String specialId) {
        this.specialId = specialId;
        return this;
    }

    /**
     * Get specialId
     *
     * @return specialId
     **/
    @ApiModelProperty(value = "")

    public String getSpecialId() {
        return specialId;
    }

    public void setSpecialId(String specialId) {
        this.specialId = specialId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(this.orderId, order.orderId) &&
                Objects.equals(this.storeId, order.storeId) &&
                Objects.equals(this.itemList, order.itemList) &&
                Objects.equals(this.tentativeAmount, order.tentativeAmount) &&
                Objects.equals(this.calculatedAmount, order.calculatedAmount) &&
                Objects.equals(this.discountAmount, order.discountAmount) &&
                Objects.equals(this.payementInformation, order.payementInformation) &&
                Objects.equals(this.status, order.status) &&
                Objects.equals(this.specialId, order.specialId);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(orderId, storeId, itemList, tentativeAmount, calculatedAmount, discountAmount,
                        payementInformation, status, specialId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Order {\n");

        sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
        sb.append("    storeId: ").append(toIndentedString(storeId)).append("\n");
        sb.append("    itemList: ").append(toIndentedString(itemList)).append("\n");
        sb.append("    tentativeAmount: ").append(toIndentedString(tentativeAmount)).append("\n");
        sb.append("    calculatedAmount: ").append(toIndentedString(calculatedAmount)).append("\n");
        sb.append("    discountAmount: ").append(toIndentedString(discountAmount)).append("\n");
        sb.append("    payementInformation: ").append(toIndentedString(payementInformation))
                .append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    specialId: ").append(toIndentedString(specialId)).append("\n");
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
