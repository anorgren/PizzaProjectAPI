/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Price;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-24T18:44:25.092Z[GMT]")
@Api(value = "prices", description = "the prices API")
public interface PricesApi {

    @ApiOperation(value = "Returns the price of a single pizza sold by Pizza Temple in cents", nickname = "getPizzaPrice", notes = "Get pricing information on pizzas sold by PizzaTemple", response = Price.class, tags={ "developers", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The cost of the requested pizza in cents", response = Price.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/prices",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Price> getPizzaPrice(@NotNull @ApiParam(value = "Size of pizza", required = true) @Valid @RequestParam(value = "size", required = true) String size,@ApiParam(value = "Topping to include on pizza") @Valid @RequestParam(value = "toppings", required = false) List<String> toppings);

}