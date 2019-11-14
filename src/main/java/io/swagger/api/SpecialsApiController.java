package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.*;
import io.swagger.repository.SpecialsRepository;
import io.swagger.model.Special;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-06T23:28:45.897Z[GMT]")
@Controller
public class SpecialsApiController implements SpecialsApi {

    private static final Logger log = LoggerFactory.getLogger(SpecialsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private SpecialsRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public SpecialsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Special>> getSpecials() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
          List<Special> specials = repository.findAll();
          if (specials == null) {
            return new ResponseEntity<List<Special>>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
          return new ResponseEntity<List<Special>>(specials, HttpStatus.OK);
        }
        return new ResponseEntity<List<Special>>(HttpStatus.NOT_IMPLEMENTED);
    }

}