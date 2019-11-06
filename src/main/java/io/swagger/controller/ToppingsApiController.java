package io.swagger.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.ToppingsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Topping;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class ToppingsApiController implements ToppingsApi {

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public ToppingsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<List<Topping>> getToppings() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<Topping>>(getToppingList(), HttpStatus.OK);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Topping>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<Topping>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Topping> getToppingsbyName(@ApiParam(value = "toppingName", required = true) @PathVariable("name") String name) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Topping>(getTopping(name), HttpStatus.OK);
      } catch (IllegalArgumentException e) {
        log.error("Illegal Argument Error: " + e.getMessage());
        return new ResponseEntity<Topping>(HttpStatus.BAD_REQUEST);
      }
      catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Topping>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Topping>(HttpStatus.NOT_IMPLEMENTED);
  }

  private List<Topping> getToppingList() throws IOException {
    String toppingListJson = new String(Files.readAllBytes(Paths.get("ToppingList.json")), StandardCharsets.UTF_8);
    List<Topping> toppingList = objectMapper.readValue(toppingListJson, new TypeReference<List<Topping>>() {
    });
    return toppingList;
  }

  private Topping getTopping(String toppingName) throws IOException, IllegalArgumentException {
    List<Topping> toppingList = getToppingList();
    if (toppingList.stream().anyMatch(topping -> topping.getToppingName().toLowerCase().equals(toppingName.toLowerCase()))) {
      return toppingList.stream().filter(topping -> topping.getToppingName().toLowerCase().equals(toppingName.toLowerCase())).findFirst().get();
    } else {}
    throw new IllegalArgumentException("Topping Not Found");
  }

}
