package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Dessert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T08:56:40.405Z[GMT]")
@Controller
public class DessertsApiController implements DessertsApi {

    private static final Logger log = LoggerFactory.getLogger(DessertsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public DessertsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Dessert>> getDesserts() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Dessert>>(objectMapper.readValue("[ {\n  \"price\" : 4.99,\n  \"dessertName\" : \"double chocolate brownies\",\n  \"description\" : \"four large gooey chocolate brownies with chocolate chunks\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n}, {\n  \"price\" : 4.99,\n  \"dessertName\" : \"double chocolate brownies\",\n  \"description\" : \"four large gooey chocolate brownies with chocolate chunks\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Dessert>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Dessert>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Dessert>> getDessertsByName(@ApiParam(value = "dessertName", required = true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Dessert>>(objectMapper.readValue("[ {\n  \"price\" : 4.99,\n  \"dessertName\" : \"double chocolate brownies\",\n  \"description\" : \"four large gooey chocolate brownies with chocolate chunks\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n}, {\n  \"price\" : 4.99,\n  \"dessertName\" : \"double chocolate brownies\",\n  \"description\" : \"four large gooey chocolate brownies with chocolate chunks\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Dessert>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Dessert>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
