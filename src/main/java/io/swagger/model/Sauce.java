package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.SauceRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Sauce
 */
@Document(collection = "Sauces")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T22:37:07.679Z[GMT]")
public class Sauce   {
  @JsonProperty("sauceName")
  private String sauceName = null;

  @JsonProperty("dietaryProperties")
  @Valid
  private Map<DietaryProperty, Boolean> dietaryProperties = new HashMap<DietaryProperty, Boolean>();

  public Sauce sauceName(String sauceName) {
    this.sauceName = sauceName;
    return this;
  }

  public static void initialize(SauceRepository repository) {
    if (repository.count() > 0) {
      return;
    }
    DataConfiguration.backfillSauceRepository(repository);
  }

  /**
   * Get sauceName
   * @return sauceName
  **/
  @ApiModelProperty(example = "original", required = true, value = "")
      @NotNull

    public String getSauceName() {
    return sauceName;
  }

  public Sauce dietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
    this.dietaryProperties = dietaryProperties;
    return this;
  }

  public Sauce putDietaryPropertiesItem(DietaryProperty key, Boolean dietaryPropertiesItem) {
    this.dietaryProperties.put(key, dietaryPropertiesItem);
    return this;
  }

  /**
   * Get dietaryProperties
   * @return dietaryProperties
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Map<DietaryProperty, Boolean> getDietaryProperties() {
    return dietaryProperties;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sauce sauce = (Sauce) o;
    return Objects.equals(this.sauceName, sauce.sauceName) &&
        Objects.equals(this.dietaryProperties, sauce.dietaryProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sauceName, dietaryProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sauce {\n");
    
    sb.append("    sauceName: ").append(toIndentedString(sauceName)).append("\n");
    sb.append("    dietaryProperties: ").append(toIndentedString(dietaryProperties)).append("\n");
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
