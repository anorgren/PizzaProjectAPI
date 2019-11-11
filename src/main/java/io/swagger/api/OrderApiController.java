package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import io.swagger.model.ItemList;
import io.swagger.model.Order;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:33:40.208Z[GMT]")
@Controller
public class OrderApiController implements OrderApi {

    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Order> createOrder(@ApiParam(value = "") @Valid @RequestParam(value = "itemList", required = false) ItemList itemList) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"payementInformation\" : {\n    \"cardSecurityCode\" : \"cardSecurityCode\",\n    \"cardExpiry\" : \"01/20\",\n    \"nameOnCard\" : \"Jon Doe\",\n    \"cardNumber\" : \"cardNumber\"\n  },\n  \"orderId\" : \"1\",\n  \"itemList\" : {\n    \"orderItems\" : [ {\n      \"itemType\" : \"Item\"\n    }, {\n      \"itemType\" : \"Item\"\n    } ]\n  },\n  \"storeId\" : \"1\",\n  \"tentativeAmount\" : {\n    \"priceInCents\" : 1000\n  },\n  \"status\" : \"Created\"\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Order>> getOrders() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Order>>(objectMapper.readValue("[ {\n  \"payementInformation\" : {\n    \"cardSecurityCode\" : \"cardSecurityCode\",\n    \"cardExpiry\" : \"01/20\",\n    \"nameOnCard\" : \"Jon Doe\",\n    \"cardNumber\" : \"cardNumber\"\n  },\n  \"orderId\" : \"1\",\n  \"itemList\" : {\n    \"orderItems\" : [ {\n      \"itemType\" : \"Item\"\n    }, {\n      \"itemType\" : \"Item\"\n    } ]\n  },\n  \"storeId\" : \"1\",\n  \"tentativeAmount\" : {\n    \"priceInCents\" : 1000\n  },\n  \"status\" : \"Created\"\n}, {\n  \"payementInformation\" : {\n    \"cardSecurityCode\" : \"cardSecurityCode\",\n    \"cardExpiry\" : \"01/20\",\n    \"nameOnCard\" : \"Jon Doe\",\n    \"cardNumber\" : \"cardNumber\"\n  },\n  \"orderId\" : \"1\",\n  \"itemList\" : {\n    \"orderItems\" : [ {\n      \"itemType\" : \"Item\"\n    }, {\n      \"itemType\" : \"Item\"\n    } ]\n  },\n  \"storeId\" : \"1\",\n  \"tentativeAmount\" : {\n    \"priceInCents\" : 1000\n  },\n  \"status\" : \"Created\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Order>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Order>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> getOrdersById(@ApiParam(value = "orderId",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"payementInformation\" : {\n    \"cardSecurityCode\" : \"cardSecurityCode\",\n    \"cardExpiry\" : \"01/20\",\n    \"nameOnCard\" : \"Jon Doe\",\n    \"cardNumber\" : \"cardNumber\"\n  },\n  \"orderId\" : \"1\",\n  \"itemList\" : {\n    \"orderItems\" : [ {\n      \"itemType\" : \"Item\"\n    }, {\n      \"itemType\" : \"Item\"\n    } ]\n  },\n  \"storeId\" : \"1\",\n  \"tentativeAmount\" : {\n    \"priceInCents\" : 1000\n  },\n  \"status\" : \"Created\"\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> updateOrder(@ApiParam(value = "orderId",required=true) @PathVariable("id") String id,@NotNull @ApiParam(value = "updated ItemList", required = true) @Valid @RequestParam(value = "itemList", required = true) ItemList itemList) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"payementInformation\" : {\n    \"cardSecurityCode\" : \"cardSecurityCode\",\n    \"cardExpiry\" : \"01/20\",\n    \"nameOnCard\" : \"Jon Doe\",\n    \"cardNumber\" : \"cardNumber\"\n  },\n  \"orderId\" : \"1\",\n  \"itemList\" : {\n    \"orderItems\" : [ {\n      \"itemType\" : \"Item\"\n    }, {\n      \"itemType\" : \"Item\"\n    } ]\n  },\n  \"storeId\" : \"1\",\n  \"tentativeAmount\" : {\n    \"priceInCents\" : 1000\n  },\n  \"status\" : \"Created\"\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

}
