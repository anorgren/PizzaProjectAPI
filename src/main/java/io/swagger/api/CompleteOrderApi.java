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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Order;
import io.swagger.model.PaymentInformation;
import io.swagger.model.Price;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
@Api(value = "completeOrder", description = "the completeOrder API")
public interface CompleteOrderApi {

    @ApiOperation(value = "Completes the given order", nickname = "completeOrder", notes = "Finalizes the order with payement information and marking order complete. Once completed the order can not be modified. ", response = Order.class, tags={ "developers", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Completed Order Record", response = Order.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/completeOrder",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Order> completeOrder(@ApiParam(value = "orderId",required=true) @PathVariable("id") String id,@NotNull @ApiParam(value = "updated ItemList", required = true) @Valid @RequestParam(value = "paymentInformation", required = true) PaymentInformation paymentInformation,@NotNull @ApiParam(value = "tentative amount of order in cents", required = true) @Valid @RequestParam(value = "tentativeAmount", required = true) Price tentativeAmount);

}
