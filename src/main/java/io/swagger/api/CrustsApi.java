/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Crust;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2019-11-10T22:37:07.679Z[GMT]")
@Api(value = "crusts", description = "the crusts API")
public interface CrustsApi {

    @ApiOperation(value = "Returns information about the given crust.", nickname = "getCrustByName",
            notes = "Get information of the given crust. ", response = Crust.class, tags = {"crusts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Information about the given crust.", response = Crust.class),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/crusts/{crustName}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Crust> getCrustByName(
            @ApiParam(value = "crustName", required = true) @PathVariable("crustName") String crustName);


    @ApiOperation(value = "Returns all crust options.", nickname = "getCrusts", notes = "Get list of all crusts. ",
            response = Crust.class, responseContainer = "List", tags = {"crusts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all crusts.", response = Crust.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/crusts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Crust>> getCrusts();

}
