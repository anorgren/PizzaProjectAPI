package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Sauce;
import io.swagger.repository.SauceRepository;
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
public class SaucesApiController implements SaucesApi {
    private final String HEADER_VALUE = "Accept";
    private final String HEADER_CONTENTS = "application/json";

    private static final Logger log = LoggerFactory.getLogger(SaucesApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private SauceRepository sauceRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public SaucesApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<Sauce> getSauceByName(@ApiParam(value = "sauceName",required=true) @PathVariable("sauceName") String sauceName) {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            Sauce sauce = sauceRepository.getSauceBySauceName(sauceName.toLowerCase());
            if (sauce == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Sauce>(sauce, HttpStatus.OK);
        }

        return new ResponseEntity<Sauce>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Sauce>> getSauces() {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            List<Sauce> sauces = sauceRepository.findAll();
            if (sauces == null) {
                return new ResponseEntity<List<Sauce>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Sauce>>(sauces, HttpStatus.OK);
        }
        return new ResponseEntity<List<Sauce>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
