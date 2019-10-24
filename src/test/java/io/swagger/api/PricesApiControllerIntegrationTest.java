package io.swagger.api;

import java.math.BigDecimal;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricesApiControllerIntegrationTest {

    @Autowired
    private PricesApi api;

    @Test
    public void getPizzaPriceTest() throws Exception {
        String size = "size_example";
        List<String> toppings = Arrays.asList("toppings_example");
        ResponseEntity<Integer> responseEntity = api.getPizzaPrice(size, toppings);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
