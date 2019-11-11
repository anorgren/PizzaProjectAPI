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
import io.swagger.model.Pizza;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
@Controller
public class PizzasApiController implements PizzasApi {

    private static final Logger log = LoggerFactory.getLogger(PizzasApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PizzasApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

        public ResponseEntity<Pizza> createPizza(@NotNull @ApiParam(value = "size description", required = true) @Valid @RequestParam(value = "size", required = true) String size,@NotNull @ApiParam(value = "crustName", required = true) @Valid @RequestParam(value = "crustName", required = true) String crustName,@NotNull @ApiParam(value = "sauceName", required = true) @Valid @RequestParam(value = "sauceName", required = true) String sauceName,@ApiParam(value = "pizza name") @Valid @RequestParam(value = "pizzaName", required = false) String pizzaName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Pizza>(objectMapper.readValue("\"\"", Pizza.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Pizza>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Pizza>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<Pizza> getPizzaByName(@ApiParam(value = "pizzaName",required=true) @PathVariable("pizzaName") String pizzaName) {
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
