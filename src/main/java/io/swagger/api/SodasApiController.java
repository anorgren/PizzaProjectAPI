package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Soda;
import io.swagger.repository.SodaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2019-11-10T08:56:40.405Z[GMT]")
@Controller
public class SodasApiController implements SodasApi {

    private final String HEADER_VALUE = "Accept";
    private final String HEADER_CONTENTS = "application/json";

    private static final Logger log = LoggerFactory.getLogger(SodasApiController.class);

    @Autowired
    private SodaRepository repository;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SodasApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<List<Soda>> getSodas() {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            List<Soda> sodas = new LinkedList<>();
            sodas = repository.findAll();
            if (sodas == null) {
                return new ResponseEntity<List<Soda>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Soda>>(sodas, HttpStatus.OK);
        }

        return new ResponseEntity<List<Soda>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Soda>> getSodasByName(
            @ApiParam(value = "sodaName", required = true) @PathVariable("name") String name) {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            List<Soda> sodas = new LinkedList<>();
            sodas = repository.getSodasBySodaName(name.toLowerCase());
            if (sodas == null) {
                return new ResponseEntity<List<Soda>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Soda>>(sodas, HttpStatus.OK);
        }
        return new ResponseEntity<List<Soda>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
