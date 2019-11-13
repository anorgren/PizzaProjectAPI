package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Dessert;
import io.swagger.repository.DessertRepository;
import io.swagger.service.DessertService;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
@Controller
public class DessertsApiController implements DessertsApi {

    private static final Logger log = LoggerFactory.getLogger(DessertsApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private DessertRepository repository;

    @Autowired
    public DessertsApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<List<Dessert>> getDesserts() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Dessert> desserts = new LinkedList<>();
            desserts = repository.findAll();
            if (desserts == null) {
                return new ResponseEntity<List<Dessert>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Dessert>>(desserts, HttpStatus.OK);
        }
        return new ResponseEntity<List<Dessert>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Dessert> getDessertsByName(@ApiParam(value = "dessertName", required = true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Dessert dessert = repository.findDessertByDessertName(name.toLowerCase());
            if (dessert == null) {
                return new ResponseEntity<Dessert>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Dessert>(dessert, HttpStatus.OK);
        }
        return new ResponseEntity<Dessert>(HttpStatus.NOT_IMPLEMENTED);
    }

}
