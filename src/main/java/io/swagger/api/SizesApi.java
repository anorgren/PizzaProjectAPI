/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.PizzaSize;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Api(value = "sizes", description = "the sizes API")
public interface SizesApi {

  @ApiOperation(value = "returns all pizza sizes", nickname = "getSizes", notes = "Get list of all pizza sizes ", response = PizzaSize.class, responseContainer = "List", tags = {
      "developers",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "list of all pizza sizes", response = PizzaSize.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "bad input parameter")})
  @RequestMapping(value = "/sizes",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<PizzaSize>> getSizes();

}

