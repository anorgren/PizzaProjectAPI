package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Topping;
import io.swagger.service.ToppingService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class ToppingsApiController implements ToppingsApi {

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);

  private final HttpServletRequest request;

  @Autowired
  private ToppingService toppingService;

  @Autowired
  public ToppingsApiController(HttpServletRequest request) {
    this.request = request;
  }

  public ResponseEntity<List<Topping>> getToppings() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      final List<Topping> toppingList = toppingService.getAllToppings();
      if (toppingList == null) {
        return new ResponseEntity<>(toppingList, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<List<Topping>>(toppingList, HttpStatus.OK);
    }
    return new ResponseEntity<List<Topping>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Topping> getToppingsByName(
      @ApiParam(value = "toppingName", required = true) @PathVariable("name") String name) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
        final Topping topping = toppingService.getTopping(name);
        if (topping == null) {
          return new ResponseEntity<Topping>(topping, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Topping>(topping, HttpStatus.OK);
    }
    return new ResponseEntity<Topping>(HttpStatus.NOT_IMPLEMENTED);
  }

}
