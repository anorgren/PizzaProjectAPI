package io.swagger.api;

import io.swagger.model.Breadstick;
import io.swagger.repository.BreadstickRepository;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
@Controller
public class BreadsticksApiController implements BreadsticksApi {

    private final String HEADER_VALUE = "Accept";
    private final String HEADER_CONTENTS = "application/json";

    private static final Logger log = LoggerFactory.getLogger(BreadsticksApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private BreadstickRepository repository;

    @Autowired
    public BreadsticksApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<List<Breadstick>> getBreadsticks() {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            List<Breadstick> breadsticks = new LinkedList<>();
            breadsticks = repository.findAll();
            if (breadsticks == null) {
                return new ResponseEntity<List<Breadstick>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Breadstick>>(breadsticks, HttpStatus.OK);
        }
        return new ResponseEntity<List<Breadstick>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
