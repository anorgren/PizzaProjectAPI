package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Order;
import io.swagger.model.Price;
import io.swagger.repository.OrderRepository;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-24T18:44:25.092Z[GMT]")
@Controller
public class PricesApiController implements PricesApi {

  private static final Logger log = LoggerFactory.getLogger(PricesApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private final Integer SMALL_BASE_PRICE = 800;
  private final Integer MEDIUM_BASE_PRICE = 1000;
  private final Integer LARGE_BASE_PRICE = 1200;
  private final Integer DOLLARS_TO_CENTS = 100;
  private Integer toppingsPrice = 0;

  @Autowired
  private OrderRepository repository;

  @org.springframework.beans.factory.annotation.Autowired
  public PricesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Price> getOrderPrice(@ApiParam(value = "orderId", required = true) @PathVariable("orderId") String orderId) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        Order order = repository.findByOrderId(orderId);
        if (order == null) {
          return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Price>(order.getTentativeAmount(), HttpStatus.OK);

      } catch (Exception e) {
        log.error("Error getting price", e.getMessage());
        return new ResponseEntity<Price>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Price>(HttpStatus.NOT_IMPLEMENTED);
  }


  /**
   * Calculates the price of a pizza with a given size and toppings.
   *
   * @param size     The size of the pizza (small, medium, large)
   * @param toppings A list of the names of all the toppings
   * @return The price of a pizza
   */
  public ResponseEntity<Price> getPizzaPrice(
          @NotNull @ApiParam(value = "Size of pizza", required = true)
          @Valid @RequestParam(value = "size", required = true) String size,
          @ApiParam(value = "Topping to include on pizza")
          @Valid @RequestParam(value = "toppings", required = false) List<String> toppings) {
    return null;
  }
}

