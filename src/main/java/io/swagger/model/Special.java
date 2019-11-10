package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Special
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-06T23:28:45.897Z[GMT]")
public class Special   {
  @JsonProperty("specialId")
  private String specialId = null;

  @JsonProperty("description")
  private String description = null;

  public Special specialId(String specialId) {
    this.specialId = specialId;
    return this;
  }

  /**
   * Get specialId
   * @return specialId
   **/
  @ApiModelProperty(example = "BOGO", required = true, value = "")
  @NotNull

  public String getSpecialId() {
    return specialId;
  }

  public void setSpecialId(String specialId) {
    this.specialId = specialId;
  }

  public Special description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @ApiModelProperty(example = "Receive the cheapest item for free when you purchase two or more items.", required = true, value = "")
  @NotNull

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Special special = (Special) o;
    return Objects.equals(this.specialId, special.specialId) &&
            Objects.equals(this.description, special.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(specialId, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Special {\n");

    sb.append("    specialId: ").append(toIndentedString(specialId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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