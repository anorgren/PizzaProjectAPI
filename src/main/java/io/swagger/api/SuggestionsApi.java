/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.PizzaSuggestion;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-22T18:16:30.929Z[GMT]")
@Api(value = "suggestions", description = "the suggestions API")
public interface SuggestionsApi {

  @ApiOperation(value = "Returns a suggested number of pizzas to order given the number of adults and children that will be enjoying it.", nickname = "getNumberOfPizzas", notes = "Suggests how many pizzas to order based on the number of people in the ordering group.", response = PizzaSuggestion.class, tags = {
      "developers",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The number of small, medium, and large pizzas to order", response = PizzaSuggestion.class),
      @ApiResponse(code = 400, message = "bad input parameter")})
  @RequestMapping(value = "/suggestions",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<PizzaSuggestion> getNumberOfPizzas(
      @NotNull @ApiParam(value = "Number of adults", required = true) @Valid @RequestParam(value = "adults", required = true) Integer adults,
      @NotNull @ApiParam(value = "Number of children", required = true) @Valid @RequestParam(value = "children", required = true) Integer children,
      @ApiParam(value = "The preferred size, if given all suggested pizzas will be this size. Must be a valid size (small, medium, large).") @Valid @RequestParam(value = "preferredSize", required = false) String preferredSize);

}
