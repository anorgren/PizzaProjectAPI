package io.swagger.api;

import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.model.PizzaSize;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
public class SizesApiController implements SizesApi {

  private static final Logger log = LoggerFactory.getLogger(SizesApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public SizesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<List<PizzaSize>> getSizes() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<PizzaSize>>(
            objectMapper.readValue("[ \"small\", \"medium\", \"large\" ]", List.class),
            HttpStatus.OK);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<PizzaSize>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<List<PizzaSize>>(HttpStatus.NOT_IMPLEMENTED);
  }

  private List<PizzaSize> getPizzaSizesList() throws IOException {
    String sizeListJson = new String(Files.readAllBytes(Paths.get("SizesList.json")),
        StandardCharsets.UTF_8);
    List<PizzaSize> sizeList = objectMapper
        .readValue(sizeListJson, new TypeReference<List<PizzaSize>>(){});
    return sizeList;
  }
}
