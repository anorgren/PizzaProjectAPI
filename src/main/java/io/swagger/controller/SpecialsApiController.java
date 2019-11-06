package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.SpecialsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.model.Advertisement;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class SpecialsApiController implements SpecialsApi {

    private static final Logger log = LoggerFactory.getLogger(SpecialsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SpecialsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Map<String, List<Advertisement>>> getSpecials() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Map<String, List<Advertisement>>>(objectMapper.readValue("{\n  \"key\" : [ {\n    \"advertisementType\" : \"Special\",\n    \"marketingContent\" : \"Buy my pizza\"\n  }, {\n    \"advertisementType\" : \"Special\",\n    \"marketingContent\" : \"Buy my pizza\"\n  } ]\n}", Map.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Map<String, List<Advertisement>>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Map<String, List<Advertisement>>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
