package io.swagger.api;

import io.swagger.model.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-30T04:43:18.635Z[GMT]")
@Controller
public class StoresApiController implements StoresApi {

    private static final Logger log = LoggerFactory.getLogger(StoresApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StoresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Store>> getStores() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Store>>(objectMapper.readValue("[ {\n  \"DiateryRestrictions\" : {\n    \"key\" : true\n  },\n  \"advertisements\" : [ {\n    \"advertiesmentType\" : \"Special\",\n    \"marketingContent\" : \"Buy my pizza\"\n  }, {\n    \"advertiesmentType\" : \"Special\",\n    \"marketingContent\" : \"Buy my pizza\"\n  } ],\n  \"address\" : \"101 Fremont Ave, Seattle, WA 12345\",\n  \"availableToppings\" : [ {\n    \"dietryProperties\" : {\n      \"key\" : true\n    },\n    \"toppingName\" : \"Tomato\",\n    \"price\" : 1\n  }, {\n    \"dietryProperties\" : {\n      \"key\" : true\n    },\n    \"toppingName\" : \"Tomato\",\n    \"price\" : 1\n  } ],\n  \"branchName\" : \"Fremont Branch\",\n  \"id\" : \"1\",\n  \"availableSizes\" : [ \"small\", \"small\" ]\n}, {\n  \"DiateryRestrictions\" : {\n    \"key\" : true\n  },\n  \"advertisements\" : [ {\n    \"advertiesmentType\" : \"Special\",\n    \"marketingContent\" : \"Buy my pizza\"\n  }, {\n    \"advertiesmentType\" : \"Special\",\n    \"marketingContent\" : \"Buy my pizza\"\n  } ],\n  \"address\" : \"101 Fremont Ave, Seattle, WA 12345\",\n  \"availableToppings\" : [ {\n    \"dietryProperties\" : {\n      \"key\" : true\n    },\n    \"toppingName\" : \"Tomato\",\n    \"price\" : 1\n  }, {\n    \"dietryProperties\" : {\n      \"key\" : true\n    },\n    \"toppingName\" : \"Tomato\",\n    \"price\" : 1\n  } ],\n  \"branchName\" : \"Fremont Branch\",\n  \"id\" : \"1\",\n  \"availableSizes\" : [ \"small\", \"small\" ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Store>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Store>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
