package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class ToppingsApiController implements ToppingsApi {

    private final String HEADER_VALUE = "Accept";
    private final String HEADER_CONTENTS = "application/json";

    private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    public ToppingsApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<List<ToppingRepository.ToppingName>> getToppings() {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            final List<ToppingRepository.ToppingName> toppingList = toppingRepository
                    .findToppingByToppingNameExists(true);
            if (toppingList == null) {
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<ToppingRepository.ToppingName>>(toppingList, HttpStatus.OK);
        }
        return new ResponseEntity<List<ToppingRepository.ToppingName>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Topping> getToppingsByName(
            @ApiParam(value = "toppingName", required = true) @PathVariable("name") String name) {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            final Topping topping = toppingRepository.findToppingByToppingName(name.toLowerCase());
            if (topping == null) {
                return new ResponseEntity<Topping>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Topping>(topping, HttpStatus.OK);
        }
        return new ResponseEntity<Topping>(HttpStatus.NOT_IMPLEMENTED);
    }

}
