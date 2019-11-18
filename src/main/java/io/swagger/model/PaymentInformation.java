package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

/**
 * PaymentInformation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
public class PaymentInformation {

  @JsonProperty("cardNumber")
  private String cardNumber = null;

  @JsonProperty("cardExpiry")
  private String cardExpiry = null;

  @JsonProperty("nameOnCard")
  private String nameOnCard = null;

  @JsonProperty("cardSecurityCode")
  private String cardSecurityCode = null;

  public PaymentInformation cardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

  /**
   * Get cardNumber
   *
   * @return cardNumber
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Size(min = 15, max = 16)
  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public PaymentInformation cardExpiry(String cardExpiry) {
    this.cardExpiry = cardExpiry;
    return this;
  }

  /**
   * Get cardExpiry
   *
   * @return cardExpiry
   **/
  @ApiModelProperty(example = "01/2020", required = true, value = "")
  @NotNull

  public String getCardExpiry() {
    return cardExpiry;
  }

  public void setCardExpiry(String cardExpiry) {
    this.cardExpiry = cardExpiry;
  }

  public PaymentInformation nameOnCard(String nameOnCard) {
    this.nameOnCard = nameOnCard;
    return this;
  }

  /**
   * Get nameOnCard
   *
   * @return nameOnCard
   **/
  @ApiModelProperty(example = "Jon Doe", required = true, value = "")
  @NotNull

  public String getNameOnCard() {
    return nameOnCard;
  }

  public void setNameOnCard(String nameOnCard) {
    this.nameOnCard = nameOnCard;
  }

  public PaymentInformation cardSecurityCode(String cardSecurityCode) {
    this.cardSecurityCode = cardSecurityCode;
    return this;
  }

  /**
   * Get cardSecurityCode
   *
   * @return cardSecurityCode
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Size(min = 3, max = 4)
  public String getCardSecurityCode() {
    return cardSecurityCode;
  }

  public void setCardSecurityCode(String cardSecurityCode) {
    this.cardSecurityCode = cardSecurityCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentInformation paymentInformation = (PaymentInformation) o;
    return Objects.equals(this.cardNumber, paymentInformation.cardNumber) &&
        Objects.equals(this.cardExpiry, paymentInformation.cardExpiry) &&
        Objects.equals(this.nameOnCard, paymentInformation.nameOnCard) &&
        Objects.equals(this.cardSecurityCode, paymentInformation.cardSecurityCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardNumber, cardExpiry, nameOnCard, cardSecurityCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentInformation {\n");

    sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n");
    sb.append("    cardExpiry: ").append(toIndentedString(cardExpiry)).append("\n");
    sb.append("    nameOnCard: ").append(toIndentedString(nameOnCard)).append("\n");
    sb.append("    cardSecurityCode: ").append(toIndentedString(cardSecurityCode)).append("\n");
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
