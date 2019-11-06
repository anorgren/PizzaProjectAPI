/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Advertisement;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Api(value = "specials", description = "the specials API")
public interface SpecialsApi {

  @ApiOperation(value = "returns list of specials in all stores", nickname = "getSpecials", notes = "get list of all specials for all stores ", response = List.class, responseContainer = "Map", tags = {
      "developers",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "list of all specials for all stores", response = List.class, responseContainer = "Map")})
  @RequestMapping(value = "/specials",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Map<String, List<Advertisement>>> getSpecials();

}
