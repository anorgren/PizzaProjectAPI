package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.AdvertisementType;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Advertisement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-30T04:43:18.635Z[GMT]")
public class Advertisement   {
  @JsonProperty("advertiesmentType")
  private AdvertisementType advertiesmentType = null;

  @JsonProperty("marketingContent")
  private String marketingContent = null;

  public Advertisement advertiesmentType(AdvertisementType advertiesmentType) {
    this.advertiesmentType = advertiesmentType;
    return this;
  }

  /**
   * Get advertiesmentType
   * @return advertiesmentType
  **/
  @ApiModelProperty(value = "")

  @Valid
  public AdvertisementType getAdvertiesmentType() {
    return advertiesmentType;
  }

  public void setAdvertiesmentType(AdvertisementType advertiesmentType) {
    this.advertiesmentType = advertiesmentType;
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
    return Objects.equals(this.advertiesmentType, advertisement.advertiesmentType) &&
        Objects.equals(this.marketingContent, advertisement.marketingContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(advertiesmentType, marketingContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Advertisement {\n");
    
    sb.append("    advertiesmentType: ").append(toIndentedString(advertiesmentType)).append("\n");
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
