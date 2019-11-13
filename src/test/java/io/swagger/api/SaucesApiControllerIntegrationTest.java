package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Dessert;
import io.swagger.model.DietaryProperty;
import io.swagger.model.Sauce;
import io.swagger.repository.DessertRepository;
import io.swagger.repository.SauceRepository;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SaucesApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {SaucesApiController.class, TestContext.class, WebApplicationContext.class})
public class SaucesApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SauceRepository repository;

    private ObjectMapper objectMapper;

    private Sauce original;
    private Sauce robust;
    private HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
    private List<Sauce> sauces;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();

        vegetarianGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        original = new Sauce();
        robust = new Sauce();
        original.sauceName("original").dietaryProperties(vegetarianGlutenFree);
        robust.sauceName("robust").dietaryProperties(vegetarianGlutenFree);

        sauces = Arrays.asList(original, robust);

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
        this.mockMvc.perform(get("/sauces")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }


    @Test
    public void getSaucesOneInRepository() throws Exception {
        List<Sauce> singleObject = Arrays.asList(original);
        String stringSingleObject = objectMapper.writeValueAsString(singleObject);
        when(repository.findAll()).thenReturn(singleObject);
        this.mockMvc.perform(get("/sauces")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringSingleObject));
    }

    @Test
    public void getSaucesMultipleReturned() throws Exception {
        String stringMultipleObjects = objectMapper.writeValueAsString(sauces);
        when(repository.findAll()).thenReturn(sauces);
        this.mockMvc.perform(get("/sauces")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringMultipleObjects));
    }

    @Test
    public void getSauceByNameValidName() throws Exception {
        String objectToGet = objectMapper.writeValueAsString(original);
        when(repository.getSauceBySauceName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Sauce sauce : sauces) {
                        if (sauce.getSauceName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return sauce;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/sauces/original")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectToGet));
    }

    @Test
    public void getDessertsByNameValidNameMixedCase() throws Exception {
        String objectToGet = objectMapper.writeValueAsString(original);
        when(repository.getSauceBySauceName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Sauce sauce : sauces) {
                        if (sauce.getSauceName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return sauce;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/sauces/Original")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectToGet));
    }

    @Test
    public void getDessertsByNameInvalidName() throws Exception {
        when(repository.getSauceBySauceName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Sauce sauce : sauces) {
                        if (sauce.getSauceName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return sauce;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/sauces/invalidName")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void getDessertByNameTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/sauces/original")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void getDessertTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/sauces")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }
}

