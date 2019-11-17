package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Crust;
import io.swagger.repository.CrustRepository;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-10T22:37:07.679Z[GMT]")
@Controller
public class CrustsApiController implements CrustsApi {

  private final String HEADER_VALUE = "Accept";
  private final String HEADER_CONTENTS = "application/json";

  private static final Logger log = LoggerFactory.getLogger(CrustsApiController.class);

  private final HttpServletRequest request;

  @Autowired
  private CrustRepository repository;

  @Autowired
  public CrustsApiController(HttpServletRequest request) {
    this.request = request;
  }

  public ResponseEntity<Crust> getCrustByName(
      @ApiParam(value = "crustName", required = true) @PathVariable("crustName") String crustName) {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      Crust crust = repository.getCrustByCrustName(crustName.toLowerCase());
      if (crust == null) {
        return new ResponseEntity<Crust>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Crust>(crust, HttpStatus.OK);
    }

    return new ResponseEntity<Crust>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Crust>> getCrusts() {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      List<Crust> crusts = repository.findAll();
      if (crusts == null) {
        return new ResponseEntity<List<Crust>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<List<Crust>>(crusts, HttpStatus.OK);
    }

    return new ResponseEntity<List<Crust>>(HttpStatus.NOT_IMPLEMENTED);
  }

}
