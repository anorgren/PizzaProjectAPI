package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Soda;
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
public class SodasApiController implements SodasApi {

    private static final Logger log = LoggerFactory.getLogger(SodasApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SodasApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Soda>> getSodas() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Soda>>(objectMapper.readValue("[ {\n  \"size\" : \"SIX_PACK\",\n  \"price\" : 1.99,\n  \"sodaName\" : \"sprite\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n}, {\n  \"size\" : \"SIX_PACK\",\n  \"price\" : 1.99,\n  \"sodaName\" : \"sprite\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Soda>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Soda>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Soda>> getSodasByName(@ApiParam(value = "sodaName", required = true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Soda>>(objectMapper.readValue("[ {\n  \"size\" : \"SIX_PACK\",\n  \"price\" : 1.99,\n  \"sodaName\" : \"sprite\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n}, {\n  \"size\" : \"SIX_PACK\",\n  \"price\" : 1.99,\n  \"sodaName\" : \"sprite\",\n  \"dietaryProperties\" : {\n    \"key\" : true\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Soda>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Soda>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
