package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * Price
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-24T18:44:25.092Z[GMT]")
public class Price {

  @JsonProperty("priceInCents")
  private Integer priceInCents = null;

  public Price priceInCents(Integer priceInCents) {
    this.priceInCents = priceInCents;
    return this;
  }

  /**
   * Get priceInCents
   *
   * @return priceInCents
   **/
  @ApiModelProperty(example = "1000", required = true, value = "")
  @NotNull

  public Integer getPriceInCents() {
    return priceInCents;
  }

  public void setPriceInCents(Integer priceInCents) {
    this.priceInCents = priceInCents;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Price price = (Price) o;
    return Objects.equals(this.priceInCents, price.priceInCents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priceInCents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Price {\n");

    sb.append("    priceInCents: ").append(toIndentedString(priceInCents)).append("\n");
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
