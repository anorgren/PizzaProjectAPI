
package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.PizzaSizeRepository;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;


@Document(collection = "PizzaSizes")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-30T04:43:18.635Z[GMT]")
public class PizzaSize {

  public static void initialize(PizzaSizeRepository repository) {
    if (repository.count() > 0) {
      return;
    }
  }

  @JsonProperty("sizeDescription")
  private String sizeDescription = null;
  @JsonProperty("sizeInInches")
  private Integer sizeInInches = null;

  public PizzaSize(String sizeDescription, Integer sizeInInches) {
    this.sizeDescription = sizeDescription;
    this.sizeInInches = sizeInInches;
  }

  @ApiModelProperty(example = "large", required = true, value = "")
  @NotNull
  public String getSizeDescription() {
    return sizeDescription;
  }

  @ApiModelProperty(example = "16", required = true, value = "")
  @NotNull
  public Integer getSizeInInches() {
    return sizeInInches;
  }
}