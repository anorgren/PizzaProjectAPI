package io.swagger.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                return new ResponseEntity<List<Store>>(lookupStores(), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Store>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Store>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Store> getStoresById(@ApiParam(value = "StoreId",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Store>(lookupStoresById(id), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                log.error("Illegal Argument Error: " + e.getMessage());
                return new ResponseEntity<Store>(HttpStatus.BAD_REQUEST);
            }
            catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Store>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Store>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    private List<Store> lookupStores() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("StoreList.json")));
        List<Store> stores = objectMapper.readValue(json,new TypeReference<List<Store>>(){});
        return stores;
    }

    private Store lookupStoresById(String storeId) throws IllegalArgumentException, IOException {
        List<Store> stores = lookupStores();
        if(stores.stream().anyMatch(store->store.getId().equals(storeId))){
            return stores.stream().filter(store -> store.getId().equals(storeId)).findFirst().get();
        } else {
            throw new IllegalArgumentException("Store Id Not Found");
        }
    }
}
