/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Order;
import io.swagger.model.PaymentInformation;
import io.swagger.model.Receipt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2019-11-11T04:07:33.221Z[GMT]")
@Api(value = "completeOrder", description = "the completeOrder API")
public interface CompleteOrderApi {

    @ApiOperation(value = "Completes the order of the provided order Id.", nickname = "completeOrder",
            notes = "Finalizes the order with payement information and marking order complete.  " +
                    "Once completed the order can not be modified. ", response = Order.class, tags = {"order",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completed Order Record", response = Order.class),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/completeOrder",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Receipt> completeOrder(
            @NotNull @ApiParam(value = "orderId", required = true)
            @Valid @RequestParam(value = "id", required = true) String id,
            @NotNull @ApiParam(value = "tentative amount of order in cents", required = true)
            @Valid @RequestParam(value = "tentativeAmount", required = true) int tentativeAmount,
            @ApiParam(value = "Payment Information") @Valid @RequestBody PaymentInformation body);

}
