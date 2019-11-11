/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Dessert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
@Api(value = "desserts", description = "the desserts API")
public interface DessertsApi {

    @ApiOperation(value = "returns desserts", nickname = "getDesserts", notes = "Get list of all desserts ", response = Dessert.class, responseContainer = "List", tags = {"developers",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list of all desserts", response = Dessert.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/desserts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Dessert>> getDesserts();


    @ApiOperation(value = "returns dessert information about the given dessert.", nickname = "getDessertsByName", notes = "Get information of the given dessert ", response = Dessert.class, responseContainer = "List", tags = {"developers",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "information about the given dessert", response = Dessert.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/desserts/{name}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Dessert> getDessertsByName(@ApiParam(value = "dessertName", required = true) @PathVariable("name") String name);

}
