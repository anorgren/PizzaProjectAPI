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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Crust;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.Sauce;
import io.swagger.model.Topping;
import io.swagger.service.CrustService;
import io.swagger.service.PizzaSizeService;
import io.swagger.service.SauceService;
import io.swagger.service.ToppingService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
@Controller
public class PizzasApiController implements PizzasApi {

  private static final Logger log = LoggerFactory.getLogger(PizzasApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  PizzaSizeService pizzaSizeService;

  @Autowired
  CrustService crustService;

  @Autowired
  SauceService sauceService;

  @Autowired
  ToppingService toppingService;

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
                                           @ApiParam(value = "topping name list(max 5 toppings)") @Valid @RequestParam(value = "toppings name List", required = true) List<String> toppingNames) throws ApiException{
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        if(toppingNames.size() > MAX_ALLOWED_TOPPINGS){
          throw new IllegalArgumentException("Number of Toppings exceeded");
        }
        PizzaSize pizzaSize = pizzaSizeService.getPizzaSizeBySizeDescription(size.toLowerCase());
        if(pizzaSize == null){
          throw new IllegalArgumentException("Invalid Pizza size");
        }
        Crust pizzaCrust = crustService.getCrustByName(crustName.toLowerCase());
        if(pizzaCrust == null){
          throw new IllegalArgumentException("Invalid Pizza crust");
        }
        Sauce pizzaSauce = sauceService.getSauceBySauceName(sauceName.toLowerCase());
        if(pizzaSauce == null){
          throw new IllegalArgumentException("Invalid Pizza sauce");
        }
        List<Topping> toppingList = new ArrayList<>();
        toppingNames.forEach(toppingName -> {
          Topping topping = toppingService.getTopping(toppingName.toLowerCase());
          if(topping == null){
            throw new IllegalArgumentException("Invalid topping: " + toppingName);
          }
          toppingList.add(topping);
        });
        Pizza pizza = new Pizza().size(pizzaSize)
                .crust(pizzaCrust)
                .sauce(pizzaSauce)
                .toppings(toppingList)
                .pizzaName(pizzaName);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
      } catch (IllegalArgumentException e) {
        throw new ApiException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
      }
    }
    return new ResponseEntity<Pizza>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Pizza> getPizzaByName(@ApiParam(value = "pizzaName", required = true) @PathVariable("pizzaName") String pizzaName) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

    }

    return new ResponseEntity<Pizza>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Pizza>> getPizzas() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

    }

    return new ResponseEntity<List<Pizza>>(HttpStatus.NOT_IMPLEMENTED);
  }
}
