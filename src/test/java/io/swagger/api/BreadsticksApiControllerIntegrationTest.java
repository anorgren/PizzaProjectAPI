package io.swagger.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Breadstick;
import io.swagger.model.DietaryProperty;
import io.swagger.repository.BreadstickRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BreadsticksApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
    {BreadsticksApiController.class, TestContext.class, WebApplicationContext.class})
public class BreadsticksApiControllerIntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BreadstickRepository repository;

  private ObjectMapper objectMapper;


  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

  }

  @Test
  public void contextLoads() {
    assertThat(repository).isNotNull();
  }

  @Test
  public void getBreadsticksEmptyList() throws Exception {
    when(repository.findAll()).thenReturn(null);
    this.mockMvc.perform(get("/breadsticks")
        .header("Accept", "application/json"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
  }


  @Test
  public void getBreadstickOneInRepo() throws Exception {
    Breadstick breadstick;
    HashMap<DietaryProperty, Boolean> vegetarian;

    breadstick = new Breadstick();

    vegetarian = new HashMap<>();
    vegetarian.put(DietaryProperty.VEGAN, false);
    vegetarian.put(DietaryProperty.VEGETARIAN, true);
    vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

    breadstick.withCheese(true).size(Breadstick.SizeEnum.LARGE).dietaryProperties(vegetarian);
    List<Breadstick> singleBread = Arrays.asList(breadstick);

    String stringBreadstickList = objectMapper.writeValueAsString(singleBread);
    when(repository.findAll()).thenReturn(singleBread);
    this.mockMvc.perform(get("/breadsticks")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(stringBreadstickList));
  }

  @Test
  public void getBreadsticksMultipleReturned() throws Exception {
    Breadstick breadstick;
    Breadstick breadstickTwo;
    Breadstick breadstickThree;
    List<Breadstick> breadsticks;
    HashMap<DietaryProperty, Boolean> vegetarian;

    breadstick = new Breadstick();
    breadstickTwo = new Breadstick();
    breadstickThree = new Breadstick();
    breadsticks = Arrays.asList(breadstick, breadstickTwo, breadstickThree);

    vegetarian = new HashMap<>();
    vegetarian.put(DietaryProperty.VEGAN, false);
    vegetarian.put(DietaryProperty.VEGETARIAN, true);
    vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

    breadstick.withCheese(true).size(Breadstick.SizeEnum.LARGE).dietaryProperties(vegetarian);
    breadstickTwo.withCheese(true).size(Breadstick.SizeEnum.SMALL).dietaryProperties(vegetarian);
    breadstickThree.withCheese(false).size(Breadstick.SizeEnum.LARGE).dietaryProperties(vegetarian);

    String stringBreadsticksList = objectMapper.writeValueAsString(breadsticks);
    when(repository.findAll()).thenReturn(breadsticks);
    this.mockMvc.perform(get("/breadsticks")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(stringBreadsticksList));
  }

  @Test
  public void getBreadsticksInvalidHeader() throws Exception {
    this.mockMvc.perform(get("/breadsticks")
        .header("null", "null"))
        .andExpect(status().isNotImplemented());
  }
}