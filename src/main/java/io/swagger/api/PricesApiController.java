package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-24T18:44:25.092Z[GMT]")
@Controller
public class PricesApiController {

    private static final Logger log = LoggerFactory.getLogger(PricesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final Integer SMALL_BASE_PRICE = 800;
    private final Integer MEDIUM_BASE_PRICE = 1000;
    private final Integer LARGE_BASE_PRICE = 1200;
    private final Integer DOLLARS_TO_CENTS = 100;
    private Integer toppingsPrice = 0;

    @org.springframework.beans.factory.annotation.Autowired
    public PricesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /**
     * Calculates the price of a pizza with a given size and toppings.
     *
     * @param size The size of the pizza (small, medium, large)
     * @param toppings A list of the names of all the toppings
     * @return The price of a pizza
     */
//  public ResponseEntity<Price> getPizzaPrice(
//      @NotNull @ApiParam(value = "Size of pizza", required = true)
//      @Valid @RequestParam(value = "size", required = true) String size,
//      @ApiParam(value = "Topping to include on pizza")
//      @Valid @RequestParam(value = "toppings", required = false) List<String> toppings) {
//    String accept = request.getHeader("Accept");
//    if (accept != null && accept.contains("application/json")) {
//      try {
//        if (isValidToppings(toppings) && PizzaSize.fromValue(size.toLowerCase()) != null) {
//          Price total = new Price();
//          total = total.priceInCents(getBasePrice(size) + this.toppingsPrice);
//          return new ResponseEntity<Price>(total, HttpStatus.OK);
//        } else {
//          return new ResponseEntity<Price>(new Price(), HttpStatus.BAD_REQUEST);
//        }
//      } catch (IOException e) {
//        log.error("Couldn't serialize response for content type application/json", e);
//        return new ResponseEntity<Price>(HttpStatus.INTERNAL_SERVER_ERROR);
//      }
//    }
//    return new ResponseEntity<Price>(HttpStatus.NOT_IMPLEMENTED);
//  }
//
//  /**
//   * Checks if toppings are valid (exsist in ToppingsList.json) and sets global variable
//   * toppingsPrice to the price of a topping
//   *
//   * @param toppings List of topping names, can be null
//   * @return if all topping names are valid
//   */
//  private boolean isValidToppings(List<String> toppings) throws IOException {
//    boolean allValid = true;
//    List<Topping> availableToppings = getToppingList();
//    this.toppingsPrice = 0;
//    //it is OK is no toppings are provided
//    if (toppings == null) {
//      return true;
//    }
//    for (String topping : toppings) {
//      boolean toppingValid = false;
//      for (Topping availableTopping : availableToppings) {
//        if (topping.equalsIgnoreCase(availableTopping.getToppingName())) {
//          toppingValid = true;
//          BigDecimal priceInCents = availableTopping.getPrice()
//              .multiply(new BigDecimal(DOLLARS_TO_CENTS));
//          this.toppingsPrice += priceInCents.intValue();
//        }
//      }
//      allValid = allValid && toppingValid;
//    }
//    return allValid;
//  }
//
//  private List<Topping> getToppingList() throws IOException {
//    String toppingListJson = new String(Files.readAllBytes(Paths.get("ToppingList.json")),
//        StandardCharsets.UTF_8);
//    List<Topping> toppingList = objectMapper
//        .readValue(toppingListJson, new TypeReference<List<Topping>>() {
//        });
//    return toppingList;
//  }
//
//  private Integer getBasePrice(String size) {
//    PizzaSize pizzaSize = PizzaSize.fromValue(size.toLowerCase());
//    Integer price = 0;
//    switch (pizzaSize) {
//      case SMALL:
//        price = SMALL_BASE_PRICE;
//        break;
//      case MEDIUM:
//        price = MEDIUM_BASE_PRICE;
//        break;
//      case LARGE:
//        price = LARGE_BASE_PRICE;
//        break;
//    }
//    return price;
//  }
}
