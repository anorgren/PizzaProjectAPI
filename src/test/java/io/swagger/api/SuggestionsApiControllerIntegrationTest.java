package io.swagger.api;

import io.swagger.model.PizzaSuggestion;

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
public class SuggestionsApiControllerIntegrationTest {

    @Autowired
    private SuggestionsApi api;

    @Test
    public void getNumberOfPizzasTest() throws Exception {
        Integer adults = 56;
        Integer children = 56;
        String preferredSize = "preferredSize_example";
        ResponseEntity<PizzaSuggestion> responseEntity = api.getNumberOfPizzas(adults, children, preferredSize);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
