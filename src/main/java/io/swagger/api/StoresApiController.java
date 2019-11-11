package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
import io.swagger.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    public ResponseEntity<List<Store>> getStores() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            final List<Store> storeList = storeService.getAllStores();
            if (storeList == null) {
                return new ResponseEntity<List<Store>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Store>>(storeList, HttpStatus.OK);
        }
        return new ResponseEntity<List<Store>>(HttpStatus.NOT_IMPLEMENTED);
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
