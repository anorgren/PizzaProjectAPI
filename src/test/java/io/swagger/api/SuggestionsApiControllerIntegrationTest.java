package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.PizzaSuggestion;

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
public class SuggestionsApiControllerIntegrationTest {

    @Autowired
    private SuggestionsApi api;


    @Before
    public void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Accept", "application/json");
        api = new SuggestionsApiController(request);
    }
    @Test
    public void getNumberOfPizzasTestSimpleLargeSize() throws Exception {
        Integer adults = 3;
        Integer children = 1;
        String preferredSize = "large";
        ResponseEntity<PizzaSuggestion> responseEntity = api.getNumberOfPizzas(adults, children, preferredSize);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer)2, responseEntity.getBody().getLarge());
        assertEquals((Integer)0, responseEntity.getBody().getMedium());
        assertEquals((Integer)0, responseEntity.getBody().getSmall());
    }

    @Test
    public void getNumberOfPizzasTestSimpleNoPreference() throws Exception {
        Integer adults = 3;
        Integer children = 1;
        ResponseEntity<PizzaSuggestion> responseEntity = api.getNumberOfPizzas(adults, children, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer)1, responseEntity.getBody().getLarge());
        assertEquals((Integer)0, responseEntity.getBody().getMedium());
        assertEquals((Integer)1, responseEntity.getBody().getSmall());
    }

    @Test
    public void getNumberOfPizzasTestNoPreferenceMultipleTypes() throws Exception {
        Integer adults = 4;
        Integer children = 3;
        ResponseEntity<PizzaSuggestion> responseEntity = api.getNumberOfPizzas(adults, children, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer)1, responseEntity.getBody().getLarge());
        assertEquals((Integer)1, responseEntity.getBody().getMedium());
        assertEquals((Integer)0, responseEntity.getBody().getSmall());
    }

    @Test
    public void getNumberOfPizzasTestSmallPreference() throws Exception {
        Integer adults = 6;
        Integer children = 1;
        ResponseEntity<PizzaSuggestion> responseEntity = api.getNumberOfPizzas(adults, children, "small");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer)0, responseEntity.getBody().getLarge());
        assertEquals((Integer)0, responseEntity.getBody().getMedium());
        assertEquals((Integer)4, responseEntity.getBody().getSmall());
    }

    @Test
    public void getNumberOfPizzasTestMediumPreference() throws Exception {
        Integer adults = 6;
        Integer children = 1;
        ResponseEntity<PizzaSuggestion> responseEntity = api.getNumberOfPizzas(adults, children, "medium");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((Integer)0, responseEntity.getBody().getLarge());
        assertEquals((Integer)3, responseEntity.getBody().getMedium());
        assertEquals((Integer)0, responseEntity.getBody().getSmall());
    }

    @Test
    public void getNumberOfPizzasTestBadSize() throws Exception {
        Integer adults = 6;
        Integer children = 1;
        ResponseEntity<PizzaSuggestion> responseEntity = api.getNumberOfPizzas(adults, children, "massive");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
