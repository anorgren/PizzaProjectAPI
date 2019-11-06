package io.swagger.api;

import io.swagger.model.Special;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialsApiControllerIntegrationTest {

    @Autowired
    private SpecialsApi api;

    @Test
    public void getSpecialsTest() throws Exception {
        ResponseEntity<List<Special>> responseEntity = api.getSpecials();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
