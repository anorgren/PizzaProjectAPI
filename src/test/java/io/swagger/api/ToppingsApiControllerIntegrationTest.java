package io.swagger.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.DietaryProperty;
import io.swagger.model.Topping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToppingsApiControllerIntegrationTest {

  @Autowired
  private ToppingsApi api;

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addHeader("Accept", "application/json");
    api = new ToppingsApiController(objectMapper, request);
  }

  @Test
  public void getToppingsTest_StatusOK() throws Exception {
    String toppingListJson = new String(Files.readAllBytes(Paths.get("ToppingList.json")), StandardCharsets.UTF_8);
    List<Topping> toppingLisrRef = objectMapper.readValue(toppingListJson, new TypeReference<List<Topping>>() {
    });
    ResponseEntity<List<Topping>> responseEntity = api.getToppings();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(toppingLisrRef.size(), responseEntity.getBody().size());
    assertEquals(toppingLisrRef, responseEntity.getBody());
  }

  @Test
  public void getToppingsTest_NoHeader() throws Exception {
    objectMapper = new ObjectMapper();
    MockHttpServletRequest NoHeaderRequest = new MockHttpServletRequest();
    ToppingsApi NoHeaderapi = new ToppingsApiController(objectMapper, NoHeaderRequest);
    ResponseEntity<List<Topping>> responseEntity = NoHeaderapi.getToppings();
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

  @Test
  public void getToppingsTest_FileNotFound() throws Exception {
    String fileName = "ToppingList.json";
    String newFileName = "ToppingList_new.json";
    File toppingListFile = new File(fileName);
    File toppingListTempFile = new File(newFileName);
    toppingListFile.renameTo(toppingListTempFile);
    ResponseEntity<List<Topping>> responseEntity = api.getToppings();
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    toppingListTempFile.renameTo(toppingListFile);
  }
}
