package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.SizesApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import io.swagger.model.PizzaSize;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class SizesApiController implements SizesApi {

    private static final Logger log = LoggerFactory.getLogger(SizesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SizesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<PizzaSize>> getSizes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<PizzaSize>>(getPizzaSizesList(), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<PizzaSize>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<PizzaSize>>(HttpStatus.NOT_IMPLEMENTED);
    }

    private List<PizzaSize> getPizzaSizesList() {
        return Arrays.stream(PizzaSize.values()).collect(Collectors.toList());
    }
}
