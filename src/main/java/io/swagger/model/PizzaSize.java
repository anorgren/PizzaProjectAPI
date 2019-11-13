
package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.PizzaSizeRepository;


@Document(collection = "PizzaSizes")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-30T04:43:18.635Z[GMT]")
public class PizzaSize {

  @JsonProperty("sizeDescription")
  private String sizeDescription = null;
  @JsonProperty("sizeInInches")
  private Integer sizeInInches = null;

  public static void initialize(PizzaSizeRepository repository) {
    if (repository.count() > 0) {
      return;
    }
    DataConfiguration.backfillPizzaSizesRepository(repository);
  }

  public PizzaSize() {}

  public PizzaSize(String sizeDescription, Integer sizeInInches) {
    this.sizeDescription = sizeDescription.toLowerCase();
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