package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Crust;
import io.swagger.model.DietaryProperty;
import io.swagger.repository.CrustRepository;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CrustsApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {CrustsApiController.class, TestContext.class, WebApplicationContext.class})
public class CrustApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrustRepository repository;

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
    public void getCrustsEmptyList() throws Exception {
        when(repository.findAll()).thenReturn(null);
        this.mockMvc.perform(get("/crusts")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }


    @Test
    public void getCrustsOneInRepository() throws Exception {
        Double price;
        Crust crustOriginal;
        HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
        price = 100d;

        vegetarianGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        crustOriginal = new Crust();
        crustOriginal.crustName("original").price(price);

        List<Crust> singleObject = Arrays.asList(crustOriginal);
        String stringSingleObject = objectMapper.writeValueAsString(singleObject);
        when(repository.findAll()).thenReturn(singleObject);
        this.mockMvc.perform(get("/crusts")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringSingleObject));
    }

    @Test
    public void getCrustsMultipleReturned() throws Exception {
        Double price;
        Crust crustOriginal;
        Crust crustGlutenFree;
        List<Crust> crusts;
        HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
        price = 100d;

        vegetarianGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        crustOriginal = new Crust();
        crustGlutenFree = new Crust();
        crustOriginal.crustName("original").price(price);
        crustGlutenFree.crustName("glutenfree").price(price).dietaryProperties(vegetarianGlutenFree);
        crusts = Arrays.asList(crustGlutenFree, crustOriginal);
        String stringMultipleObjects = objectMapper.writeValueAsString(crusts);

        when(repository.findAll()).thenReturn(crusts);
        this.mockMvc.perform(get("/crusts")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringMultipleObjects));
    }

    @Test
    public void getCrustsByNameValidName() throws Exception {
        Double price;
        Crust crustOriginal;
        Crust crustGlutenFree;
        List<Crust> crusts;
        HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
        price = 100d;

        vegetarianGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        crustOriginal = new Crust();
        crustGlutenFree = new Crust();
        crustOriginal.crustName("original").price(price);
        crustGlutenFree.crustName("glutenfree").price(price).dietaryProperties(vegetarianGlutenFree);
        crusts = Arrays.asList(crustGlutenFree, crustOriginal);

        String objectToGet = objectMapper.writeValueAsString(crustOriginal);
        when(repository.getCrustByCrustName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Crust crust : crusts) {
                        if (crust.getCrustName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return crust;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/crusts/original")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectToGet));
    }

    @Test
    public void getCrustsByNameInvalidName() throws Exception {
        Double price;
        Crust crustOriginal;
        Crust crustGlutenFree;
        List<Crust> crusts;
        HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
        price = 100d;

        vegetarianGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        crustOriginal = new Crust();
        crustGlutenFree = new Crust();
        crustOriginal.crustName("original").price(price);
        crustGlutenFree.crustName("glutenfree").price(price).dietaryProperties(vegetarianGlutenFree);
        crusts = Arrays.asList(crustGlutenFree, crustOriginal);

        when(repository.getCrustByCrustName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Crust crust : crusts) {
                        if (crust.getCrustName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return crust;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/toppings/invalidName")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void getCrustByNameTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/crusts/original")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void getCrustTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/crusts")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }
}
