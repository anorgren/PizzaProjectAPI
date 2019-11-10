/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Topping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Api(value = "toppings", description = "the toppings API")
public interface ToppingsApi {

    @ApiOperation(value = "returns all toppings", nickname = "getToppings", notes = "Get list of all toppings ", response = Topping.class, responseContainer = "List", tags = {
            "developers",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list of all toppings", response = Topping.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/toppings",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Topping>> getToppings();


    @ApiOperation(value = "returns topping information of the given topping", nickname = "getToppingsbyName", notes = "Get topping information of the given topping ", response = Topping.class, tags = {
            "developers",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "topping information of the given topping", response = Topping.class),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/toppings/{name}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Topping> getToppingsByName(
            @ApiParam(value = "toppingName", required = true) @PathVariable("name") String name);

}
