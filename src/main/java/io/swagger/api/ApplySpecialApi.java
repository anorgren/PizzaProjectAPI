/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Order;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T05:40:35.019Z[GMT]")
@Api(value = "applySpecial", description = "the applySpecial API")
public interface ApplySpecialApi {

  @ApiOperation(value = "Applies the given specialId to order if applicable", nickname = "applySpecial", notes = "applies the Special/Discount to order ", response = Order.class, tags = {"developers",})
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Updated Order Record", response = Order.class),
          @ApiResponse(code = 400, message = "bad input parameter")})
  @RequestMapping(value = "/applySpecial",
          produces = {"application/json"},
          method = RequestMethod.PUT)
    ResponseEntity<Order> applySpecial(@NotNull @ApiParam(value = "specialId", required = true) @Valid @RequestParam(value = "specialId", required = true) String specialId, @NotNull @ApiParam(value = "orderId", required = true) @Valid @RequestParam(value = "orderId", required = true) String orderId);

}
