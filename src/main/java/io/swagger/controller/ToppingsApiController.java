package io.swagger.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.ToppingsApi;
import io.swagger.service.ToppingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class ToppingsApiController implements ToppingsApi {

  @Autowired
  private ToppingService toppingService;

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  public ToppingsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<List<Topping>> getToppings() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<Topping>>(toppingService.getAllToppings(), HttpStatus.OK);
      } catch (IllegalArgumentException e) {
        log.error("Illegal Argument Error: " + e.getMessage());
        return new ResponseEntity<List<Topping>>(HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<List<Topping>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Topping> getToppingsbyName(
      @ApiParam(value = "toppingName", required = true) @PathVariable("name") String name) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Topping>(toppingService.getTopping(name), HttpStatus.OK);
      } catch (IllegalArgumentException e) {
        log.error("Illegal Argument Error: " + e.getMessage());
        return new ResponseEntity<Topping>(HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity<Topping>(HttpStatus.NOT_IMPLEMENTED);
  }

}
