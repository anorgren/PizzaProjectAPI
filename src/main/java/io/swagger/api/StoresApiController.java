package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2019-10-19T23:59:29.208Z[GMT]")
@Controller
public class StoresApiController implements StoresApi {

    private final String HEADER_VALUE = "Accept";
    private final String HEADER_CONTENTS = "application/json";

    private static final Logger log = LoggerFactory.getLogger(StoresApiController.class);
    private final HttpServletRequest request;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    public StoresApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<List<StoreRepository.BasicStoreInfo>> getStores() {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            final List<StoreRepository.BasicStoreInfo> storeList = storeRepository
                    .getAllByBranchIdExists(Boolean.TRUE);
            if (storeList == null) {
                return new ResponseEntity<List<StoreRepository.BasicStoreInfo>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<StoreRepository.BasicStoreInfo>>(storeList, HttpStatus.OK);
        }
        return new ResponseEntity<List<StoreRepository.BasicStoreInfo>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Store> getStoresById(
            @ApiParam(value = "BranchId", required = true) @PathVariable("id") String id) {
        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            Store store = storeRepository.findStoreByBranchId(id);
            if (store == null) {
                return new ResponseEntity<Store>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Store>(store, HttpStatus.OK);
        }
        return new ResponseEntity<Store>(HttpStatus.NOT_IMPLEMENTED);
    }
}
