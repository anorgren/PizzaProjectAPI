/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Api(value = "stores", description = "the stores API")
public interface StoresApi {

    @ApiOperation(value = "Returns all stores' branch id, name, and address.", nickname = "getStores",
            notes = "Get list of all stores ", response = Store.class, responseContainer = "List", tags = {"stores",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all stores' basic information.", response = Store.class,
                    responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/stores",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<StoreRepository.BasicStoreInfo>> getStores();


    @ApiOperation(value = "Get a particular store by its branch id.", nickname = "getStoresById",
            notes = "Get store details by id ", response = Store.class, tags = {"stores",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Details of the store of the given Id", response = Store.class),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/stores/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Store> getStoresById(
            @ApiParam(value = "StoreId", required = true) @PathVariable("id") String id);

}
