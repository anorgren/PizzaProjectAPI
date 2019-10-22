package io.swagger.api;

import java.math.BigDecimal;
import io.swagger.model.PizzaSuggestion;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-22T17:50:14.546Z[GMT]")
@Controller
public class SuggestionsApiController implements SuggestionsApi {

    private static final Logger log = LoggerFactory.getLogger(SuggestionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SuggestionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PizzaSuggestion> getNumberOfPizzas(@NotNull @ApiParam(value = "Number of adults", required = true) @Valid @RequestParam(value = "adults", required = true) BigDecimal adults,@NotNull @ApiParam(value = "Number of children", required = true) @Valid @RequestParam(value = "children", required = true) BigDecimal children,@ApiParam(value = "The preferred size, if given all suggested pizzas will be this size. Must be a valid size (small, medium, large).") @Valid @RequestParam(value = "preferredSize", required = false) String preferredSize) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PizzaSuggestion>(objectMapper.readValue("{\n  \"small\" : 2,\n  \"large\" : 0,\n  \"medium\" : 3\n}", PizzaSuggestion.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PizzaSuggestion>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PizzaSuggestion>(HttpStatus.NOT_IMPLEMENTED);
    }

}
