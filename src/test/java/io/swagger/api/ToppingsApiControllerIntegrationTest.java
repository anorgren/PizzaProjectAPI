package io.swagger.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import io.swagger.model.Topping;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToppingsApiControllerIntegrationTest {

  @Autowired
  private ToppingsApi api;

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
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
    ToppingsApi NoHeaderapi = new ToppingsApiController( NoHeaderRequest);
    ResponseEntity<List<Topping>> responseEntity = NoHeaderapi.getToppings();
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    ResponseEntity<Topping> toppingNameResponse = NoHeaderapi.getToppingsByName("garlic");
    assertEquals(HttpStatus.NOT_IMPLEMENTED, toppingNameResponse.getStatusCode());
  }

  @Test
  public void getToppingsTest_FileNotFound() throws Exception {
    String fileName = "ToppingList.json";
    String newFileName = "ToppingList_new.json";
    File toppingListFile = new File(fileName);
    File toppingListTempFile = new File(newFileName);
    toppingListFile.renameTo(toppingListTempFile);
    ResponseEntity<List<Topping>> toppingsResponse = api.getToppings();
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, toppingsResponse.getStatusCode());
    ResponseEntity<Topping> toppingsNameResponse = api.getToppingsByName("garlic");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, toppingsNameResponse.getStatusCode());
    toppingListTempFile.renameTo(toppingListFile);
  }

  @Test
  public void getToppingsByNameTest() throws Exception {
    String name = "garlic";
    String toppingListJson = new String(Files.readAllBytes(Paths.get("ToppingList.json")), StandardCharsets.UTF_8);
    List<Topping> toppingLisrRef = objectMapper.readValue(toppingListJson, new TypeReference<List<Topping>>() {
    });
    Topping topping = toppingLisrRef.stream().filter(toppingRef -> toppingRef.getToppingName().toLowerCase().equals(name.toLowerCase())).findFirst().get();
    ResponseEntity<Topping> responseEntity = api.getToppingsByName(name);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(topping,responseEntity.getBody());
  }

  @Test
  public void getToppingsByNameTest_InvalidName() throws Exception {
    String name = "name_example";
    ResponseEntity<Topping> responseEntity = api.getToppingsByName(name);
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }
}
