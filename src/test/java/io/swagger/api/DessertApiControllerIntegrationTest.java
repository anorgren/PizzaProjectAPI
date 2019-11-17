package io.swagger.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Dessert;
import io.swagger.model.DietaryProperty;
import io.swagger.repository.DessertRepository;
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
@WebMvcTest(DessertsApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {DessertsApiController.class, TestContext.class, WebApplicationContext.class})
public class DessertApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DessertRepository repository;

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
    public void getDessertsEmptyList() throws Exception {
        when(repository.findAll()).thenReturn(null);
        this.mockMvc.perform(get("/desserts")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }


    @Test
    public void getDessertsOneInRepository() throws Exception {
        Double price;
        Dessert brownies;

        HashMap<DietaryProperty, Boolean> vegetarian;
        price = 4.99d;

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        brownies = new Dessert();

        brownies.dessertName("brownies").description("BROWNIE_DESCRIPTION")
                .dietaryProperties(vegetarian).price(price);

        List<Dessert> singleObject = Arrays.asList(brownies);
        String stringSingleObject = objectMapper.writeValueAsString(singleObject);
        when(repository.findAll()).thenReturn(singleObject);
        this.mockMvc.perform(get("/desserts")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringSingleObject));
    }

    @Test
    public void getDessertsMultipleReturned() throws Exception {
        Double price;
        Dessert cookies;
        Dessert brownies;
        List<Dessert> desserts;

        HashMap<DietaryProperty, Boolean> vegetarian;
        price = 4.99d;

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        brownies = new Dessert();
        cookies = new Dessert();

        brownies.dessertName("brownies").description("BROWNIE_DESCRIPTION")
                .dietaryProperties(vegetarian).price(price);

        cookies.dessertName("cookies").description("chocolate cookies")
                .dietaryProperties(vegetarian).price(price);

        desserts = Arrays.asList(brownies, cookies);
        String stringMultipleObjects = objectMapper.writeValueAsString(desserts);
        when(repository.findAll()).thenReturn(desserts);
        this.mockMvc.perform(get("/desserts")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringMultipleObjects));
    }

    @Test
    public void getDessertsByNameValidName() throws Exception {
        Double price;
        Dessert cookies;
        Dessert brownies;
        List<Dessert> desserts;

        HashMap<DietaryProperty, Boolean> vegetarian;
        price = 4.99d;

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        brownies = new Dessert();
        cookies = new Dessert();

        brownies.dessertName("brownies").description("BROWNIE_DESCRIPTION")
                .dietaryProperties(vegetarian).price(price);

        cookies.dessertName("cookies").description("chocolate cookies")
                .dietaryProperties(vegetarian).price(price);

        desserts = Arrays.asList(brownies, cookies);
        String objectToGet = objectMapper.writeValueAsString(brownies);

        when(repository.findDessertByDessertName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Dessert dessert : desserts) {
                        if (dessert.getDessertName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return dessert;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/desserts/brownies")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectToGet));
    }

    @Test
    public void getDessertsByNameValidNameMixedCase() throws Exception {
        Double price;
        Dessert cookies;
        Dessert brownies;
        List<Dessert> desserts;

        HashMap<DietaryProperty, Boolean> vegetarian;
        price = 4.99d;

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        brownies = new Dessert();
        cookies = new Dessert();

        brownies.dessertName("brownies").description("BROWNIE_DESCRIPTION")
                .dietaryProperties(vegetarian).price(price);

        cookies.dessertName("cookies").description("chocolate cookies")
                .dietaryProperties(vegetarian).price(price);

        desserts = Arrays.asList(brownies, cookies);

        String objectToGet = objectMapper.writeValueAsString(brownies);
        when(repository.findDessertByDessertName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Dessert dessert : desserts) {
                        if (dessert.getDessertName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return dessert;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/desserts/Brownies")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectToGet));
    }

    @Test
    public void getDessertsByNameInvalidName() throws Exception {
        Double price;
        Dessert cookies;
        Dessert brownies;
        List<Dessert> desserts;

        HashMap<DietaryProperty, Boolean> vegetarian;
        price = 4.99d;

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        brownies = new Dessert();
        cookies = new Dessert();

        brownies.dessertName("brownies").description("BROWNIE_DESCRIPTION")
                .dietaryProperties(vegetarian).price(price);

        cookies.dessertName("cookies").description("chocolate cookies")
                .dietaryProperties(vegetarian).price(price);

        desserts = Arrays.asList(brownies, cookies);

        when(repository.findDessertByDessertName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Dessert dessert : desserts) {
                        if (dessert.getDessertName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return dessert;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/desserts/invalidName")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void getDessertByNameTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/desserts/brownies")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void getDessertTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/desserts")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }
}
