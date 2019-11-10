package io.swagger.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.repository.StoreRepository;
import io.swagger.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Store;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class StoresApiController implements StoresApi {

  private static final Logger log = LoggerFactory.getLogger(StoresApiController.class);
  private final HttpServletRequest request;

  @Autowired
  private StoreService storeService;

  @Autowired
  public StoresApiController(HttpServletRequest request) {
    this.request = request;
  }

  public ResponseEntity<List<StoreRepository.BasicStoreInfo>> getStores() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      final List<StoreRepository.BasicStoreInfo> storeList = storeService.getAllStores();
      if (storeList == null) {
        return new ResponseEntity<List<StoreRepository.BasicStoreInfo>>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<List<StoreRepository.BasicStoreInfo>>(storeList, HttpStatus.OK);
//      try {
//        return new ResponseEntity<List<Store>>(lookupStores(), HttpStatus.OK);
//      } catch (IOException e) {
//        log.error("Couldn't serialize response for content type application/json", e);
//        return new ResponseEntity<List<Store>>(HttpStatus.INTERNAL_SERVER_ERROR);
//      }
    }
    return new ResponseEntity<List<StoreRepository.BasicStoreInfo>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<StoreRepository.BasicStoreInfo> getStoresById(
      @ApiParam(value = "StoreId", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      StoreRepository.BasicStoreInfo store = storeService.getStoreById(id);
      if (store == null) {
        return new ResponseEntity<StoreRepository.BasicStoreInfo>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<StoreRepository.BasicStoreInfo>(store, HttpStatus.OK);
    }
    return new ResponseEntity<StoreRepository.BasicStoreInfo>(HttpStatus.NOT_IMPLEMENTED);
  }
}
