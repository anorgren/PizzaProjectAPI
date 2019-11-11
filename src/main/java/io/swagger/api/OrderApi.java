/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.ItemList;
import io.swagger.model.Order;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
@Api(value = "order", description = "the order API")
public interface OrderApi {

    @ApiOperation(value = "creates a new order", nickname = "createOrder", notes = "Creates a new order with provided ItemList(optional). Returns order object. ", response = Order.class, tags={ "developers", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Created order object with order id", response = Order.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Order> createOrder(@ApiParam(value = "") @Valid @RequestParam(value = "itemList", required = false) ItemList itemList);


    @ApiOperation(value = "returns all orders", nickname = "getOrders", notes = "Get list of all orders ", response = Order.class, responseContainer = "List", tags={ "developers", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "list of all orders", response = Order.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> getOrders();


    @ApiOperation(value = "get order by id", nickname = "getOrdersById", notes = "Get order details by id ", response = Order.class, tags={ "developers", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Details of the order of the given Id", response = Order.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/order/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Order> getOrdersById(@ApiParam(value = "orderId",required=true) @PathVariable("id") String id);


    @ApiOperation(value = "updates the given order", nickname = "updateOrder", notes = "update the given order with given parameters. ", response = Order.class, tags={ "developers", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Updated Order Record", response = Order.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/order/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Order> updateOrder(@ApiParam(value = "orderId",required=true) @PathVariable("id") String id,@NotNull @ApiParam(value = "updated ItemList", required = true) @Valid @RequestParam(value = "itemList", required = true) ItemList itemList);

}
