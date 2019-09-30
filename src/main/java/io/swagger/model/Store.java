package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Advertisement;
import io.swagger.model.PizzaSize;
import io.swagger.model.Topping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Store
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-30T04:43:18.635Z[GMT]")
public class Store   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("branchName")
  private String branchName = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("DiateryRestrictions")
  @Valid
  private Map<String, Boolean> diateryRestrictions = null;

  @JsonProperty("availableToppings")
  @Valid
  private List<Topping> availableToppings = null;

  @JsonProperty("availableSizes")
  @Valid
  private List<PizzaSize> availableSizes = null;

  @JsonProperty("advertisements")
  @Valid
  private List<Advertisement> advertisements = null;

  public Store id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Store branchName(String branchName) {
    this.branchName = branchName;
    return this;
  }

  /**
   * Get branchName
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
   * @return address
  **/
  @ApiModelProperty(example = "101 Fremont Ave, Seattle, WA 12345", value = "")

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Store diateryRestrictions(Map<String, Boolean> diateryRestrictions) {
    this.diateryRestrictions = diateryRestrictions;
    return this;
  }

  public Store putDiateryRestrictionsItem(String key, Boolean diateryRestrictionsItem) {
    if (this.diateryRestrictions == null) {
      this.diateryRestrictions = new HashMap<String, Boolean>();
    }
    this.diateryRestrictions.put(key, diateryRestrictionsItem);
    return this;
  }

  /**
   * Get diateryRestrictions
   * @return diateryRestrictions
  **/
  @ApiModelProperty(value = "")

  public Map<String, Boolean> getDiateryRestrictions() {
    return diateryRestrictions;
  }

  public void setDiateryRestrictions(Map<String, Boolean> diateryRestrictions) {
    this.diateryRestrictions = diateryRestrictions;
  }

  public Store availableToppings(List<Topping> availableToppings) {
    this.availableToppings = availableToppings;
    return this;
  }

  public Store addAvailableToppingsItem(Topping availableToppingsItem) {
    if (this.availableToppings == null) {
      this.availableToppings = new ArrayList<Topping>();
    }
    this.availableToppings.add(availableToppingsItem);
    return this;
  }

  /**
   * Get availableToppings
   * @return availableToppings
  **/
  @ApiModelProperty(value = "")
  @Valid
  public List<Topping> getAvailableToppings() {
    return availableToppings;
  }

  public void setAvailableToppings(List<Topping> availableToppings) {
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

  public Store advertisements(List<Advertisement> advertisements) {
    this.advertisements = advertisements;
    return this;
  }

  public Store addAdvertisementsItem(Advertisement advertisementsItem) {
    if (this.advertisements == null) {
      this.advertisements = new ArrayList<Advertisement>();
    }
    this.advertisements.add(advertisementsItem);
    return this;
  }

  /**
   * Get advertisements
   * @return advertisements
  **/
  @ApiModelProperty(value = "")
  @Valid
  public List<Advertisement> getAdvertisements() {
    return advertisements;
  }

  public void setAdvertisements(List<Advertisement> advertisements) {
    this.advertisements = advertisements;
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
    return Objects.equals(this.id, store.id) &&
        Objects.equals(this.branchName, store.branchName) &&
        Objects.equals(this.address, store.address) &&
        Objects.equals(this.diateryRestrictions, store.diateryRestrictions) &&
        Objects.equals(this.availableToppings, store.availableToppings) &&
        Objects.equals(this.availableSizes, store.availableSizes) &&
        Objects.equals(this.advertisements, store.advertisements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, branchName, address, diateryRestrictions, availableToppings, availableSizes, advertisements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Store {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    branchName: ").append(toIndentedString(branchName)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    diateryRestrictions: ").append(toIndentedString(diateryRestrictions)).append("\n");
    sb.append("    availableToppings: ").append(toIndentedString(availableToppings)).append("\n");
    sb.append("    availableSizes: ").append(toIndentedString(availableSizes)).append("\n");
    sb.append("    advertisements: ").append(toIndentedString(advertisements)).append("\n");
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
