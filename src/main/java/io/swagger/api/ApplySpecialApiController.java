package io.swagger.api;

import io.swagger.model.Order;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T05:40:35.019Z[GMT]")
@Controller
public class ApplySpecialApiController implements ApplySpecialApi {

    private static final Logger log = LoggerFactory.getLogger(ApplySpecialApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ApplySpecialApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Order> applySpecial(@ApiParam(value = "orderId",required=true) @PathVariable("specialId") String specialId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"payementInformation\" : {\n    \"cardSecurityCode\" : \"cardSecurityCode\",\n    \"cardExpiry\" : \"01/20\",\n    \"nameOnCard\" : \"Jon Doe\",\n    \"cardNumber\" : \"cardNumber\"\n  },\n  \"orderId\" : \"1\",\n  \"itemList\" : {\n    \"orderItems\" : [ {\n      \"itemType\" : \"Item\"\n    }, {\n      \"itemType\" : \"Item\"\n    } ]\n  },\n  \"storeId\" : \"1\",\n  \"tentativeAmount\" : {\n    \"priceInCents\" : 1000\n  },\n  \"status\" : \"Created\",\n  \"specialId\" : \"specialId\"\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

}
