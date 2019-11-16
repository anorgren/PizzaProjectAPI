package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import io.swagger.repository.*;
import org.junit.After;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PizzasApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {PizzasApiController.class, TestContext.class, WebApplicationContext.class})
public class PizzasApiControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaRepository repository;

    @MockBean
    private PizzaSizeRepository pizzaSizeRepository;

    @MockBean
    private CrustRepository crustRepository;

    @MockBean
    private SauceRepository sauceRepository;

    @MockBean
    private ToppingRepository toppingRepository;


    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void contextLoads() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void createPizza() {
    }

    @Test
    public void getPizzaByNameValidName() throws Exception {
        HashMap<DietaryProperty, Boolean> vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        Topping pepperoniTopping = new Topping();
        pepperoniTopping.toppingName("pepperoni").price(2.00)
                .dietaryProperties(vegetarian);
        Topping shreddedProvoloneCheese = new Topping();
        shreddedProvoloneCheese.toppingName("shredded provolone cheese").price(2.00)
                .dietaryProperties(vegetarian);
        Topping shreddedParmesan = new Topping();
        shreddedParmesan.toppingName("shredded parmesan asiago").price(3.45)
                .dietaryProperties(vegetarian);
        List<Topping> toppings = Arrays.asList(pepperoniTopping, shreddedParmesan, shreddedProvoloneCheese);

        PizzaSize pizzaSizeLarge = new PizzaSize("large", 16);

        Crust crustOriginal = new Crust();
        crustOriginal.crustName("original").availableSizes(Collections.singletonList(pizzaSizeLarge))
                .price(3.2).dietaryProperties(vegetarian);

        Sauce sauceOriginal = new Sauce();
        sauceOriginal.sauceName("original")
                .dietaryProperties(vegetarian);

        Pizza pizza = new Pizza();
        pizza.pizzaName("test").sauce(sauceOriginal).toppings(toppings).crust(crustOriginal).size(pizzaSizeLarge);
        Pizza pizzaTwo = new Pizza();
        pizzaTwo.pizzaName("testTwo").sauce(sauceOriginal).toppings(toppings).crust(crustOriginal).size(pizzaSizeLarge);
        List<Pizza> pizzas = Arrays.asList(pizza, pizzaTwo);

        String expected = objectMapper.writeValueAsString(pizza);
        when(repository.getPizzaByPizzaName(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Pizza pizzaInList : pizzas) {
                        if (pizzaInList.getPizzaName()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return pizzaInList;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/pizzas/test")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expected));
    }

    @Test
    public void getPizzaByNameInvalidName() throws Exception {
        this.mockMvc.perform(get("/pizzas/invalidName")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void getPizzasEmptyPizzaRepo() throws Exception {
        when(repository.findAll()).thenReturn(null);
        this.mockMvc.perform(get("/pizzas")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }

    @Test
    public void getPizzasOnePizzaInRepo() throws Exception {
        HashMap<DietaryProperty, Boolean> vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        Topping pepperoniTopping = new Topping();
        pepperoniTopping.toppingName("pepperoni").price(2.00)
                .dietaryProperties(vegetarian);
        Topping shreddedProvoloneCheese = new Topping();
        shreddedProvoloneCheese.toppingName("shredded provolone cheese").price(2.00)
                .dietaryProperties(vegetarian);
        Topping shreddedParmesan = new Topping();
        shreddedParmesan.toppingName("shredded parmesan asiago").price(3.45)
                .dietaryProperties(vegetarian);
        List<Topping> toppings = Arrays.asList(pepperoniTopping, shreddedParmesan, shreddedProvoloneCheese);

        PizzaSize pizzaSizeLarge = new PizzaSize("large", 16);

        Crust crustOriginal = new Crust();
        crustOriginal.crustName("original").availableSizes(Collections.singletonList(pizzaSizeLarge))
                .price(3.2).dietaryProperties(vegetarian);

        Sauce sauceOriginal = new Sauce();
        sauceOriginal.sauceName("original")
                .dietaryProperties(vegetarian);

        Pizza pizza = new Pizza();
        pizza.pizzaName("test").sauce(sauceOriginal).toppings(toppings).crust(crustOriginal).size(pizzaSizeLarge);
        List<Pizza> pizzaList = Collections.singletonList(pizza);

        String stringPizzaList = objectMapper.writeValueAsString(pizzaList);
        when(repository.findAll()).thenReturn(pizzaList);
        this.mockMvc.perform(get("/pizzas")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringPizzaList));

    }

    @Test
    public void getPizzasManyInRepo() throws Exception {
        HashMap<DietaryProperty, Boolean> vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        Topping pepperoniTopping = new Topping();
        pepperoniTopping.toppingName("pepperoni").price(2.00)
                .dietaryProperties(vegetarian);
        Topping shreddedProvoloneCheese = new Topping();
        shreddedProvoloneCheese.toppingName("shredded provolone cheese").price(2.00)
                .dietaryProperties(vegetarian);
        Topping shreddedParmesan = new Topping();
        shreddedParmesan.toppingName("shredded parmesan asiago").price(3.45)
                .dietaryProperties(vegetarian);
        List<Topping> toppings = Arrays.asList(pepperoniTopping, shreddedParmesan, shreddedProvoloneCheese);

        PizzaSize pizzaSizeLarge = new PizzaSize("large", 16);

        Crust crustOriginal = new Crust();
        crustOriginal.crustName("original").availableSizes(Collections.singletonList(pizzaSizeLarge))
                .price(3.2).dietaryProperties(vegetarian);

        Sauce sauceOriginal = new Sauce();
        sauceOriginal.sauceName("original")
                .dietaryProperties(vegetarian);

        Pizza pizza = new Pizza();
        pizza.pizzaName("test").sauce(sauceOriginal).toppings(toppings).crust(crustOriginal).size(pizzaSizeLarge);
        Pizza pizzaTwo = new Pizza();
        pizzaTwo.pizzaName("testTwo").sauce(sauceOriginal).toppings(toppings).crust(crustOriginal).size(pizzaSizeLarge);
        List<Pizza> pizzas = Arrays.asList(pizza, pizzaTwo);
        String stringPizzas = objectMapper.writeValueAsString(pizzas);

        when(repository.findAll()).thenReturn(pizzas);
        this.mockMvc.perform(get("/pizzas")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringPizzas));
    }

    @Test
    public void getPizzaByNameTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/pizzas/test")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void getToppingsTestInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/pizzas")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }
}