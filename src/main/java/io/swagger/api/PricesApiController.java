package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Crust;
import io.swagger.model.Order;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.Price;
import io.swagger.model.Sauce;
import io.swagger.model.Topping;
import io.swagger.repository.CrustRepository;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SauceRepository;
import io.swagger.repository.ToppingRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-24T18:44:25.092Z[GMT]")
@Controller
public class PricesApiController implements PricesApi {
  private final String HEADER_VALUE = "Accept";
  private final String HEADER_CONTENTS = "application/json";
  private final String ERROR_MESSAGE_PRICE = "Error getting price ";
  private final String ERROR_MESSAGE_ORDERS = "Error getting orders ";
  private final String ERROR_MESSAGE_TOPPINGS = "Invalid topping: ";

  private static final Logger log = LoggerFactory.getLogger(PricesApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private OrderRepository repository;

  @Autowired
  PizzaSizeRepository pizzaSizeRepository;

  @Autowired
  CrustRepository crustRepository;

  @Autowired
  SauceRepository sauceRepository;

  @Autowired
  ToppingRepository toppingRepository;

  private static final int MAX_ALLOWED_TOPPINGS = 5;

  @org.springframework.beans.factory.annotation.Autowired
  public PricesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Price> getOrderPrice(@ApiParam(value = "orderId", required = true) @PathVariable("orderId") String orderId) {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      try {
        Order order = repository.findByOrderId(orderId);
        if (order == null) {
          return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Price>(order.getTentativeAmount(), HttpStatus.OK);

      } catch (Exception e) {
        log.error(ERROR_MESSAGE_PRICE, e.getMessage());
        return new ResponseEntity<Price>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Price>(HttpStatus.NOT_IMPLEMENTED);
  }


  /**
   * Calculates the price of a pizza with a given size, toppings, sauce and crust.
   *
   * @param size     The size of the pizza (small, medium, large)
   * @param toppings A list of the names of all the toppings
   * @param crust    Crust selected for pizza
   * @param sauce    Sauce selected for pizza
   * @return The price of a pizza
   */
  public ResponseEntity<Price> getPizzaPrice(
          @NotNull @ApiParam(value = "Size of pizza", required = true)
          @Valid @RequestParam(value = "size", required = true) String size,
          @ApiParam(value = "Topping to include on pizza")
          @Valid @RequestParam(value = "toppings", required = false) List<String> toppings,
          @NotNull @ApiParam(value = "crustName", required = true) @Valid @RequestParam(value = "crustName", required = true) String crustName,
          @NotNull @ApiParam(value = "sauceName", required = true) @Valid @RequestParam(value = "sauceName", required = true) String sauceName) {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      try {
        if (toppings.size() > MAX_ALLOWED_TOPPINGS) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PizzaSize pizzaSize = pizzaSizeRepository.findPizzaSizeBySizeDescription(size.toLowerCase());
        if (pizzaSize == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Crust pizzaCrust = crustRepository.getCrustByCrustName(crustName.toLowerCase());
        if (pizzaCrust == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Sauce pizzaSauce = sauceRepository.getSauceBySauceName(sauceName.toLowerCase());
        if (pizzaSauce == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Topping> toppingList = new ArrayList<>();
        try {
          toppings.forEach(toppingName -> {
            Topping topping = toppingRepository.findToppingByToppingName(toppingName.toLowerCase());
            if (topping == null) {
              throw new IllegalArgumentException(ERROR_MESSAGE_TOPPINGS + toppingName);
            }
            toppingList.add(topping);
          });
        } catch (IllegalArgumentException e) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Pizza pizza = new Pizza().size(pizzaSize)
                .crust(pizzaCrust)
                .sauce(pizzaSauce)
                .toppings(toppingList);
        return new ResponseEntity<Price>(new Price().priceInCents((int) (pizza.getPrice() * 100)), HttpStatus.OK);
      } catch (Exception e) {
        log.error(ERROR_MESSAGE_ORDERS, e.getMessage());
        return new ResponseEntity<Price>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Price>(HttpStatus.NOT_IMPLEMENTED);
  }
}

