package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricesApiControllerIntegrationTest {

    @Autowired
    private PricesApi api;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Accept", "application/json");
        api = new PricesApiController(objectMapper, request);
    }
    @Test
    public void getPizzaPriceTestNoToppingSmall() throws Exception {
        String size = "small";
        List<String> toppings = Arrays.asList();
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer) 800, responseEntity.getBody());
    }

    @Test
    public void getPizzaPriceTestNoToppingMedium() throws Exception {
        String size = "medium";
        List<String> toppings = Arrays.asList();
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer) 1000, responseEntity.getBody());
    }

    @Test
    public void getPizzaPriceTestNoToppingLarge() throws Exception {
        String size = "large";
        List<String> toppings = Arrays.asList();
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer) 1200, responseEntity.getBody());
    }

    @Test
    public void getPizzaPriceTestInvalidSize() throws Exception {
        String size = "massive";
        List<String> toppings = Arrays.asList();
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getPizzaPriceTestOneToppingSmall() throws Exception {
        String size = "small";
        List<String> toppings = Arrays.asList("Garlic");
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer) 1000, responseEntity.getBody());
    }

    @Test
    public void getPizzaPriceTestTwoToppingSmall() throws Exception {
        String size = "small";
        List<String> toppings = Arrays.asList("Garlic", "onions");
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer) 1200, responseEntity.getBody());
    }

    @Test
    public void getPizzaPriceTestManyoppingLarge() throws Exception {
        String size = "Large";
        List<String> toppings = Arrays.asList("Garlic", "onions", "bacon", "philly steak");
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer) 2200, responseEntity.getBody());
    }

}
