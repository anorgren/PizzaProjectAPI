package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.StoreRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * Store
 */
@Document(collection = "Stores")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-20T01:15:42.292Z[GMT]")
public class Store implements StoreRepository.BasicStoreInfo {

  @JsonProperty("branchId")
  private String branchId = null;

  @JsonProperty("branchName")
  private String branchName = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("dietaryRestrictions")
  @Valid
  private Map<DietaryProperty, Boolean> dietaryRestrictions;

  @JsonProperty("availableToppings")
  @Valid
  private List<String> availableToppings;

  @JsonProperty("availableSizes")
  @Valid
  private List<PizzaSize> availableSizes;

  public static void initialize(StoreRepository repository) {
    if (repository.count() > 0) {
      return;
    }
    DataConfiguration.backfillStoresRepository(repository);
  }

  public Store id(String id) {
    this.branchId = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull
  public String getBranchId() {
    return branchId;
  }

  public void setBranchId(String branchID) {
    this.branchId = branchID;
  }

  public Store branchName(String branchName) {
    this.branchName = branchName;
    return this;
  }

  /**
   * Get branchName
   *
   * @return branchName
   **/
  @ApiModelProperty(example = "Fremont Branch", value = "")
  
    public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public Store address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   *
   * @return address
   **/
  @ApiModelProperty(example = "101 Fremont Ave, Seattle, WA 12345", value = "")
  
    public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Store dietaryRestrictions(Map<DietaryProperty, Boolean> dietaryRestrictions) {
    this.dietaryRestrictions = dietaryRestrictions;
    return this;
  }

  public Store putDietaryRestrictionsItem(DietaryProperty key, Boolean dietaryRestrictionsItem) {
    if (this.dietaryRestrictions == null) {
      this.dietaryRestrictions = new HashMap<DietaryProperty, Boolean>();
    }
    this.dietaryRestrictions.put(key, dietaryRestrictionsItem);
    return this;
  }

  /**
   * Get dietaryRestrictions
   *
   * @return dietaryRestrictions
   **/
  @ApiModelProperty(value = "")
  
    public Map<DietaryProperty, Boolean> getDietaryRestrictions() {
    return dietaryRestrictions;
  }

  public void setDietaryRestrictions(Map<DietaryProperty, Boolean> dietaryRestrictions) {
    this.dietaryRestrictions = dietaryRestrictions;
  }

  public Store availableToppings(List<String> availableToppings) {
    this.availableToppings = availableToppings;
    return this;
  }

  public Store addAvailableToppingsItem(String availableToppingsItem) {
    if (this.availableToppings == null) {
      this.availableToppings = new ArrayList<String>();
    }
    this.availableToppings.add(availableToppingsItem);
    return this;
  }

  /**
   * Get availableToppings
   *
   * @return availableToppings
   **/
  @ApiModelProperty(value = "")
  
    public List<String> getAvailableToppings() {
    return availableToppings;
  }

  public void setAvailableToppings(List<String> availableToppings) {
    this.availableToppings = availableToppings;
  }

  public Store availableSizes(List<PizzaSize> availableSizes) {
    this.availableSizes = availableSizes;
    return this;
  }

  public Store addAvailableSizesItem(PizzaSize availableSizesItem) {
    if (this.availableSizes == null) {
      this.availableSizes = new ArrayList<PizzaSize>();
    }
    this.availableSizes.add(availableSizesItem);
    return this;
  }

  /**
   * Get availableSizes
   *
   * @return availableSizes
   **/
  @ApiModelProperty(value = "")
      @Valid
    public List<PizzaSize> getAvailableSizes() {
    return availableSizes;
  }

  public void setAvailableSizes(List<PizzaSize> availableSizes) {
    this.availableSizes = availableSizes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Store store = (Store) o;
    return Objects.equals(this.branchId, store.branchId) &&
        Objects.equals(this.branchName, store.branchName) &&
        Objects.equals(this.address, store.address) &&
        Objects.equals(this.dietaryRestrictions, store.dietaryRestrictions) &&
        Objects.equals(this.availableToppings, store.availableToppings) &&
        Objects.equals(this.availableSizes, store.availableSizes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(branchId, branchName, address, dietaryRestrictions, availableToppings, availableSizes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Store {\n");

    sb.append("    id: ").append(toIndentedString(branchId)).append("\n");
    sb.append("    branchName: ").append(toIndentedString(branchName)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    dietaryRestrictions: ").append(toIndentedString(dietaryRestrictions))
        .append("\n");
    sb.append("    availableToppings: ").append(toIndentedString(availableToppings)).append("\n");
    sb.append("    availableSizes: ").append(toIndentedString(availableSizes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public String basicInfoToString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Store {\n");

    sb.append("    id: ").append(toIndentedString(branchId)).append("\n");
    sb.append("    branchName: ").append(toIndentedString(branchName)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
