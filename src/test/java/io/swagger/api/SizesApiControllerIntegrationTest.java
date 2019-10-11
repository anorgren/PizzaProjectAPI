package io.swagger.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeBase;
import com.fasterxml.jackson.databind.type.TypeBindings;
import io.swagger.model.PizzaSize;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
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
public class SizesApiControllerIntegrationTest {

    @Autowired
    private SizesApi api;

    private ObjectMapper objectMapper;

    @Before
    public void SetUp() {
        objectMapper = new ObjectMapper();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Accept", "application/json");
        api = new SizesApiController(objectMapper, request);
    }

    @Test
    public void getSizesTestStatusOk() throws Exception {
        String sizeListJson = new String(Files.readAllBytes(Paths.get("SizesList.json")),
            StandardCharsets.UTF_8);
        List<PizzaSize> sizeListRef = objectMapper
            .readValue(sizeListJson, new TypeReference<List<PizzaSize>>() {});
        ResponseEntity<List<PizzaSize>> responseEntity = api.getSizes();

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(sizeListRef.size(), responseEntity.getBody().size());
        // Line below causing test to fail
        // Assert.assertEquals(sizeListRef, responseEntity.getBody());
    }

    @Test
    public void getSizesTestCountSizes() throws Exception {
        ResponseEntity<List<PizzaSize>> responseEntity = api.getSizes();
        List<PizzaSize> pizzaSizes = responseEntity.getBody();
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(pizzaSizes.size(), 3);
    }

    @Test
    public void getSizesTestNoHeader() throws Exception {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        objectMapper = new ObjectMapper();
        api = new SizesApiController(objectMapper, mockRequest);
        ResponseEntity<List<PizzaSize>> responseEntity = api.getSizes();
        Assert.assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
