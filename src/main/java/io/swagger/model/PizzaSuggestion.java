package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * PizzaSuggestion
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-22T17:50:14.546Z[GMT]")
public class PizzaSuggestion {

  @JsonProperty("small")
  private Integer small = null;

  @JsonProperty("medium")
  private Integer medium = null;

  @JsonProperty("large")
  private Integer large = null;

  public PizzaSuggestion small(Integer small) {
    this.small = small;
    return this;
  }

  /**
   * Get small
   *
   * @return small
   **/
  @ApiModelProperty(example = "2", required = true, value = "")
  @NotNull

  public Integer getSmall() {
    return small;
  }

  public void setSmall(Integer small) {
    this.small = small;
  }

  public PizzaSuggestion medium(Integer medium) {
    this.medium = medium;
    return this;
  }

  /**
   * Get medium
   *
   * @return medium
   **/
  @ApiModelProperty(example = "3", required = true, value = "")
  @NotNull

  public Integer getMedium() {
    return medium;
  }

  public void setMedium(Integer medium) {
    this.medium = medium;
  }

  public PizzaSuggestion large(Integer large) {
    this.large = large;
    return this;
  }

  /**
   * Get large
   *
   * @return large
   **/
  @ApiModelProperty(example = "0", required = true, value = "")
  @NotNull

  public Integer getLarge() {
    return large;
  }

  public void setLarge(Integer large) {
    this.large = large;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PizzaSuggestion pizzaSuggestion = (PizzaSuggestion) o;
    return Objects.equals(this.small, pizzaSuggestion.small) &&
        Objects.equals(this.medium, pizzaSuggestion.medium) &&
        Objects.equals(this.large, pizzaSuggestion.large);
  }

  @Override
  public int hashCode() {
    return Objects.hash(small, medium, large);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PizzaSuggestion {\n");

    sb.append("    small: ").append(toIndentedString(small)).append("\n");
    sb.append("    medium: ").append(toIndentedString(medium)).append("\n");
    sb.append("    large: ").append(toIndentedString(large)).append("\n");
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
