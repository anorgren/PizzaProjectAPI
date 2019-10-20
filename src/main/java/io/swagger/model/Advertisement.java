package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * Advertisement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
public class Advertisement   {
  @JsonProperty("advertisementType")
  private AdvertisementType advertisementType = null;

  @JsonProperty("marketingContent")
  private String marketingContent = null;

  public Advertisement advertisementType(AdvertisementType advertisementType) {
    this.advertisementType = advertisementType;
    return this;
  }

  /**
   * Get advertisementType
   * @return advertisementType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public AdvertisementType getAdvertisementType() {
    return advertisementType;
  }

  public void setAdvertisementType(AdvertisementType advertisementType) {
    this.advertisementType = advertisementType;
  }

  public Advertisement marketingContent(String marketingContent) {
    this.marketingContent = marketingContent;
    return this;
  }

  /**
   * Get marketingContent
   * @return marketingContent
  **/
  @ApiModelProperty(example = "Buy my pizza", value = "")

  public String getMarketingContent() {
    return marketingContent;
  }

  public void setMarketingContent(String marketingContent) {
    this.marketingContent = marketingContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Advertisement advertisement = (Advertisement) o;
    return Objects.equals(this.advertisementType, advertisement.advertisementType) &&
        Objects.equals(this.marketingContent, advertisement.marketingContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(advertisementType, marketingContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Advertisement {\n");
    
    sb.append("    advertisementType: ").append(toIndentedString(advertisementType)).append("\n");
    sb.append("    marketingContent: ").append(toIndentedString(marketingContent)).append("\n");
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
