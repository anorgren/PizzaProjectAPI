package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Crust;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.Sauce;
import io.swagger.model.Topping;
import io.swagger.repository.CrustRepository;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SauceRepository;
import io.swagger.repository.ToppingRepository;
import java.util.ArrayList;
import java.util.Collections;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
@Controller
public class PizzasApiController implements PizzasApi {
  private final String HEADER_VALUE = "Accept";
  private final String HEADER_CONTENTS = "application/json";
  private final String ERROR_MESSAGE_TOPPINGS = "Invalid topping: ";

  private static final Logger log = LoggerFactory.getLogger(PizzasApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  PizzaSizeRepository pizzaSizeRepository;

  @Autowired
  CrustRepository crustRepository;

  @Autowired
  SauceRepository sauceRepository;

  @Autowired
  ToppingRepository toppingRepository;

  @Autowired
  PizzaRepository pizzaRepository;

  private static final int MAX_ALLOWED_TOPPINGS = 5;

  @org.springframework.beans.factory.annotation.Autowired
  public PizzasApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Pizza> createPizza(@NotNull @ApiParam(value = "size description", required = true) @Valid @RequestParam(value = "size", required = true) String size,
                                           @NotNull @ApiParam(value = "crustName", required = true) @Valid @RequestParam(value = "crustName", required = true) String crustName,
                                           @NotNull @ApiParam(value = "sauceName", required = true) @Valid @RequestParam(value = "sauceName", required = true) String sauceName,
                                           @ApiParam(value = "pizza name") @Valid @RequestParam(value = "pizzaName", required = false) String pizzaName,
                                           @ApiParam(value = "topping name list(max 5 toppings)") @Valid @RequestParam(value = "toppingName", required = true) List<String> toppingNames) throws ApiException {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      try {
        if (toppingNames.size() > MAX_ALLOWED_TOPPINGS) {
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
          toppingNames.forEach(toppingName -> {
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
        if (pizzaName != null) {
          pizza.pizzaName(pizzaName);
        }
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Error creating order", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Pizza>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Pizza> getPizzaByName(@ApiParam(value = "pizzaName", required = true) @PathVariable("pizzaName") String pizzaName) {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      Pizza responsePizza = pizzaRepository.getPizzaByPizzaName(pizzaName.toLowerCase());
      if (responsePizza == null) {
        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Pizza>(responsePizza, HttpStatus.OK);
    }

    return new ResponseEntity<Pizza>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Pizza>> getPizzas() {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      List<Pizza> responsePizzas = pizzaRepository.findAll();
      if (responsePizzas == null) {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<List<Pizza>>(responsePizzas, HttpStatus.OK);
    }

    return new ResponseEntity<List<Pizza>>(HttpStatus.NOT_IMPLEMENTED);
  }
}
