package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Breadstick;
import io.swagger.service.BreadstickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
@Controller
public class BreadsticksApiController implements BreadsticksApi {

    private static final Logger log = LoggerFactory.getLogger(BreadsticksApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private BreadstickService breadstickService;

    @Autowired
    public BreadsticksApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<List<Breadstick>> getBreadsticks() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Breadstick> breadsticks = new LinkedList<>();
            breadsticks = breadstickService.getAllBreadsticks();
            if (breadsticks == null) {
                return new ResponseEntity<List<Breadstick>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Breadstick>>(breadsticks, HttpStatus.OK);
        }
        return new ResponseEntity<List<Breadstick>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
