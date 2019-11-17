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
import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import java.util.Arrays;
import java.util.Collections;
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
@WebMvcTest(ToppingsApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {ToppingsApiController.class, TestContext.class, WebApplicationContext.class})
public class ToppingsApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToppingRepository repository;

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
    public void getToppingsEmptyToppingList() throws Exception {
        when(repository.findToppingByToppingNameExists(true)).thenReturn(null);
        this.mockMvc.perform(get("/toppings")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }


    @Test
    public void getToppingsOneTopping() throws Exception {
        Topping pepperoniName = new Topping();
        pepperoniName.toppingName("pepperoni");

        List<ToppingRepository.ToppingName> singleTopping = Collections.singletonList(pepperoniName);
        String stringToppingsList = objectMapper.writeValueAsString(singleTopping);

        when(repository.findToppingByToppingNameExists(true)).thenReturn(singleTopping);
        this.mockMvc.perform(get("/toppings")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringToppingsList));
    }

    @Test
    public void getToppingsMultipleToppingsReturned() throws Exception {
        Double price = 5d;
        HashMap<DietaryProperty, Boolean> vegetarianGlutenFree = new HashMap<>();
        HashMap<DietaryProperty, Boolean> notVegetarianIsGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGETARIAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        Topping pepperoni = new Topping();
        pepperoni.toppingName("pepperoni").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);

        Topping ham = new Topping();
        ham.toppingName("ham").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);

        Topping cheese = new Topping();
        cheese.toppingName("cheese").price(price)
                .dietaryProperties(vegetarianGlutenFree);

        List<ToppingRepository.ToppingName> toppings;
        toppings = Arrays.asList(pepperoni, ham, cheese);

        String stringToppingsList = objectMapper.writeValueAsString(toppings);

        when(repository.findToppingByToppingNameExists(true)).thenReturn(toppings);
        this.mockMvc.perform(get("/toppings")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringToppingsList));
    }

    @Test
    public void getToppingsByNameValidName() throws Exception {
        Double price = 5d;
        HashMap<DietaryProperty, Boolean> vegetarianGlutenFree = new HashMap<>();
        HashMap<DietaryProperty, Boolean> notVegetarianIsGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGETARIAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        Topping pepperoni = new Topping();
        pepperoni.toppingName("pepperoni").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);

        Topping ham = new Topping();
        ham.toppingName("ham").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);

        Topping cheese = new Topping();
        cheese.toppingName("cheese").price(price)
                .dietaryProperties(vegetarianGlutenFree);

        List<Topping> toppings = Arrays.asList(ham, cheese, pepperoni);

        String stringHam = objectMapper.writeValueAsString(ham);
        when(repository.findToppingByToppingName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Topping topping : toppings) {
                        if (topping.getToppingName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return topping;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/toppings/ham")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringHam));
    }

    @Test
    public void getToppingsByNameInvalidName() throws Exception {
        Double price = 5d;
        HashMap<DietaryProperty, Boolean> vegetarianGlutenFree = new HashMap<>();
        HashMap<DietaryProperty, Boolean> notVegetarianIsGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGETARIAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        Topping pepperoni = new Topping();
        pepperoni.toppingName("pepperoni").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);

        Topping ham = new Topping();
        ham.toppingName("ham").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);

        Topping cheese = new Topping();
        cheese.toppingName("cheese").price(price)
                .dietaryProperties(vegetarianGlutenFree);

        List<Topping> toppings = Arrays.asList(ham, cheese, pepperoni);

        when(repository.findToppingByToppingName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Topping topping : toppings) {
                        if (topping.getToppingName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return topping;
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
    public void getToppingByNameTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/toppings/ham")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void getToppingsTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/toppings")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }
}
