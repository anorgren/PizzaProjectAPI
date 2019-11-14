package io.swagger.api;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class SizesApiController implements SizesApi {

    private static final Logger log = LoggerFactory.getLogger(SizesApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private PizzaSizeRepository pizzaSizeRepository;

    @Autowired
    public SizesApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<List<PizzaSize>> getSizes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<PizzaSize> pizzaSizes = pizzaSizeRepository.findAll();
            if (pizzaSizes == null) {
                return new ResponseEntity<List<PizzaSize>>(Collections.EMPTY_LIST, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<PizzaSize>>(pizzaSizes, HttpStatus.OK);

        }
        return new ResponseEntity<List<PizzaSize>>(HttpStatus.NOT_IMPLEMENTED);
    }

}