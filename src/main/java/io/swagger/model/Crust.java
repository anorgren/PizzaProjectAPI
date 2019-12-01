package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.configuration.DataConfiguration;
import io.swagger.repository.CrustRepository;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Crust
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T22:37:07.679Z[GMT]")
public class Crust {

    @JsonProperty("crustName")
    private String crustName = null;

    @JsonProperty("dietaryProperties")
    @Valid
    private Map<DietaryProperty, Boolean> dietaryProperties = new HashMap<DietaryProperty, Boolean>();

    @JsonProperty("price")
    private Double price = null;

    @JsonProperty("availableSizes")
    @Valid
    private List<PizzaSize> availableSizes = new ArrayList<PizzaSize>();

    public static void initialize(CrustRepository repository) {
        if (repository.count() > 0) {
            return;
        }
        DataConfiguration.backfillCrustRepository(repository);
    }

    public Crust crustName(String crustName) {
        this.crustName = crustName;
        return this;
    }

    /**
     * Get crustName
     *
     * @return crustName
     **/
    @ApiModelProperty(example = "thin crust", value = "")

    public String getCrustName() {
        return crustName;
    }

    public void setCrustName(String crustName) {
        this.crustName = crustName;
    }

    public Crust dietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
        this.dietaryProperties = dietaryProperties;
        return this;
    }

    public Crust putDietaryPropertiesItem(DietaryProperty key, Boolean dietaryPropertiesItem) {
        this.dietaryProperties.put(key, dietaryPropertiesItem);
        return this;
    }

    /**
     * Get dietaryProperties
     *
     * @return dietaryProperties
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Map<DietaryProperty, Boolean> getDietaryProperties() {
        return dietaryProperties;
    }

    public void setDietaryProperties(Map<DietaryProperty, Boolean> dietaryProperties) {
        this.dietaryProperties = dietaryProperties;
    }

    public Crust price(Double price) {
        this.price = price;
        return this;
    }

    /**
     * Get price
     *
     * @return price
     **/
    @ApiModelProperty(example = "1", required = true, value = "")
    @NotNull

    @Valid
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Crust availableSizes(List<PizzaSize> availableSizes) {
        this.availableSizes = availableSizes;
        return this;
    }

    public Crust addAvailableSizesItem(PizzaSize availableSizesItem) {
        this.availableSizes.add(availableSizesItem);
        return this;
    }

    /**
     * Get availableSizes
     *
     * @return availableSizes
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
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
        Crust crust = (Crust) o;
        return Objects.equals(this.crustName, crust.crustName) &&
                Objects.equals(this.dietaryProperties, crust.dietaryProperties) &&
                Objects.equals(this.price, crust.price) &&
                Objects.equals(this.availableSizes, crust.availableSizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crustName, dietaryProperties, price, availableSizes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Crust {\n");

        sb.append("    crustName: ").append(toIndentedString(crustName)).append("\n");
        sb.append("    dietaryProperties: ").append(toIndentedString(dietaryProperties)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    availableSizes: ").append(toIndentedString(availableSizes)).append("\n");
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
