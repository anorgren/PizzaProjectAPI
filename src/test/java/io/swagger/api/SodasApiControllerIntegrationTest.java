package io.swagger.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.DietaryProperty;
import io.swagger.model.Soda;
import io.swagger.repository.SodaRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
@WebMvcTest(SodasApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
    {SodasApiController.class, TestContext.class, WebApplicationContext.class})
public class SodasApiControllerIntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SodaRepository repository;

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
  public void getSaucesEmptyRepository() throws Exception {
    when(repository.findAll()).thenReturn(null);
    this.mockMvc.perform(get("/sodas")
        .header("Accept", "application/json"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
  }


  @Test
  public void getSodasOneInRepository() throws Exception {

    Soda sixPack;
    HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;

    veganVegetarianGlutenFree = new HashMap<>();
    veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

    sixPack = new Soda();
    sixPack.size(Soda.SizeEnum.SIX_PACK).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);
    List<Soda> singleObject = Collections.singletonList(sixPack);
    String stringSingleObject = objectMapper.writeValueAsString(singleObject);

    when(repository.findAll()).thenReturn(singleObject);
    this.mockMvc.perform(get("/sodas")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(stringSingleObject));
  }

  @Test
  public void getSodasMultipleReturned() throws Exception {
    Soda sixPack;
    Soda twoLiter;
    Soda twentyOunce;
    HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    List<Soda> sodas;

    veganVegetarianGlutenFree = new HashMap<>();
    veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

    sixPack = new Soda();
    sixPack.size(Soda.SizeEnum.SIX_PACK).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);
    twoLiter = new Soda();
    twoLiter.size(Soda.SizeEnum.TWO_LITER).sodaName("sprite")
        .dietaryProperties(veganVegetarianGlutenFree);
    twentyOunce = new Soda();
    twentyOunce.size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);

    sodas = Arrays.asList(sixPack, twoLiter, twentyOunce);
    String stringMultipleObjects = objectMapper.writeValueAsString(sodas);

    when(repository.findAll()).thenReturn(sodas);
    this.mockMvc.perform(get("/sodas")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(stringMultipleObjects));
  }

  @Test
  public void getSodaByNameValidName() throws Exception {
    Soda sixPack;
    Soda twoLiter;
    Soda twentyOunce;
    HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    List<Soda> sodas;

    veganVegetarianGlutenFree = new HashMap<>();
    veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

    sixPack = new Soda();
    sixPack.size(Soda.SizeEnum.SIX_PACK).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);
    twoLiter = new Soda();
    twoLiter.size(Soda.SizeEnum.TWO_LITER).sodaName("sprite")
        .dietaryProperties(veganVegetarianGlutenFree);
    twentyOunce = new Soda();
    twentyOunce.size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);

    sodas = Arrays.asList(sixPack, twoLiter, twentyOunce);
    String objectToGet = objectMapper.writeValueAsString(Arrays.asList(twentyOunce, sixPack));

    when(repository.getSodasBySodaName(any()))
        .thenAnswer(invocationOnMock -> sodas.stream()
            .filter(soda -> soda.getSodaName().equals(invocationOnMock.getArguments()[0]))
            .collect(Collectors.toList()));
    this.mockMvc.perform(get("/sodas/coke")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(objectToGet));
  }

  @Test
  public void getSodasByNameValidNameMixedCase() throws Exception {
    Soda sixPack;
    Soda twoLiter;
    Soda twentyOunce;
    HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    List<Soda> sodas;

    veganVegetarianGlutenFree = new HashMap<>();
    veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

    sixPack = new Soda();
    sixPack.size(Soda.SizeEnum.SIX_PACK).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);
    twoLiter = new Soda();
    twoLiter.size(Soda.SizeEnum.TWO_LITER).sodaName("sprite")
        .dietaryProperties(veganVegetarianGlutenFree);
    twentyOunce = new Soda();
    twentyOunce.size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);
    sodas = Arrays.asList(sixPack, twoLiter, twentyOunce);
    String objectToGet = objectMapper.writeValueAsString(Arrays.asList(twentyOunce, sixPack));

    when(repository.getSodasBySodaName(any()))
        .thenAnswer(invocationOnMock -> sodas.stream()
            .filter(soda -> soda.getSodaName().equals(invocationOnMock.getArguments()[0]))
            .collect(Collectors.toList()));
    this.mockMvc.perform(get("/sodas/Coke")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(objectToGet));
  }

  @Test
  public void getSodasByNameInvalidName() throws Exception {
    Soda sixPack;
    Soda twoLiter;
    Soda twentyOunce;
    HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    List<Soda> sodas;

    veganVegetarianGlutenFree = new HashMap<>();
    veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

    sixPack = new Soda();
    sixPack.size(Soda.SizeEnum.SIX_PACK).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);
    twoLiter = new Soda();
    twoLiter.size(Soda.SizeEnum.TWO_LITER).sodaName("sprite")
        .dietaryProperties(veganVegetarianGlutenFree);
    twentyOunce = new Soda();
    twentyOunce.size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE).sodaName("coke")
        .dietaryProperties(veganVegetarianGlutenFree);
    sodas = Arrays.asList(sixPack, twoLiter, twentyOunce);

    when(repository.getSodasBySodaName(any()))
        .thenAnswer(invocationOnMock -> {
          List<Soda> matching = new ArrayList<>();
          for (Soda soda : sodas) {
            if (soda.getSodaName()
                .equals(invocationOnMock.getArguments()[0])) {
              matching.add(soda);
            }
          }
          if (matching.size() == 0) {
            return null;
          }
          return matching;
        });
    this.mockMvc.perform(get("/sodas/invalidName")
        .header("Accept", "application/json"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getSodaByNameTestInvalidHeader() throws Exception {
    this.mockMvc.perform(get("/sodas/coke")
        .header("null", "null"))
        .andExpect(status().isNotImplemented());
  }

  @Test
  public void getDessertTestInvalidHeader() throws Exception {
    this.mockMvc.perform(get("/sodas")
        .header("null", "null"))
        .andExpect(status().isNotImplemented());
  }
}
