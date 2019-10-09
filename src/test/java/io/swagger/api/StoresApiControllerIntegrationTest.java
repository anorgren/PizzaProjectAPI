package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.DiateryProperty;
import io.swagger.model.PizzaSize;
import io.swagger.model.Store;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoresApiControllerIntegrationTest {

    @Autowired
    private StoresApi api;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Accept", "application/json");
        api = new StoresApiController(objectMapper, request);
    }
    @Test
    public void getStoresTestOKStatus() throws Exception {
        ResponseEntity<List<Store>> responseEntity = api.getStores();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void getStoresTestFirstStore() throws Exception {
        ResponseEntity<List<Store>> responseEntity = api.getStores();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Store> stores = responseEntity.getBody();
        Store first = stores.get(0);
        assertEquals(first.getAddress(), "101 Fremont Ave, Seattle, WA 12345");
        assertEquals(first.getId(), "1");
        assertEquals(first.getBranchName(), "Fremont Branch");
        Map<String, Boolean> dietProp =  first.getDiateryRestrictions();
        assertEquals(dietProp.get("Vegan"), true);
        assertEquals(dietProp.get("Gluten Free"), false);
        assertEquals(dietProp.get("Vegetarian"), false);
        assertTrue(first.getAvailableSizes().contains(PizzaSize.MEDIUM));
        assertTrue(first.getAvailableSizes().contains(PizzaSize.LARGE));
    }

    @Test
    public void getStoresTestNoHeader() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        objectMapper = new ObjectMapper();
        api = new StoresApiController(objectMapper, request);
        ResponseEntity<List<Store>> responseEntity = api.getStores();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
