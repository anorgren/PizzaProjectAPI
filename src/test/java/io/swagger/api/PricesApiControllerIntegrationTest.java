package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Crust;
import io.swagger.model.DietaryProperty;
import io.swagger.model.Order;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.Price;
import io.swagger.model.Sauce;
import io.swagger.model.Topping;
import io.swagger.repository.CrustRepository;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SauceRepository;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PricesApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
    {PricesApiController.class, TestContext.class, WebApplicationContext.class})
public class PricesApiControllerIntegrationTest {

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

  @MockBean
  private OrderRepository orderRepository;

  private static final String TEST_ORDER_ID = "TestOrderId";
  private static final Integer TEST_ORDER_PRICE = 100;


  private ObjectMapper objectMapper;

  @Before
  public void setUp() throws Exception {
    objectMapper = new ObjectMapper();

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testGetOrderPrice() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID).tentativeAmount(new Price().priceInCents(TEST_ORDER_PRICE));
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    MockHttpServletResponse response = this.mockMvc.perform(get("/prices/" + TEST_ORDER_ID)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    Price price = objectMapper.readValue(response.getContentAsString(), Price.class);
    assertEquals(TEST_ORDER_PRICE, price.getPriceInCents());
  }

  @Test
  public void testGetOrderPrice_InvalidOrder() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(null);
    MockHttpServletResponse response = this.mockMvc.perform(get("/prices/" + TEST_ORDER_ID)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testGetOrderPrice_Exception() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenThrow(new RuntimeException());
    MockHttpServletResponse response = this.mockMvc.perform(get("/prices/" + TEST_ORDER_ID)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(500, response.getStatus());
  }

  @Test
  public void testGetOrderPrice_NoHeader() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenThrow(new RuntimeException());
    MockHttpServletResponse response = this.mockMvc.perform(get("/prices/" + TEST_ORDER_ID))
        .andReturn().getResponse();
    assertEquals(501, response.getStatus());
  }

  @Test
  public void getPizzaPrice() throws Exception {
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
    List<Topping> toppings = Arrays
        .asList(pepperoniTopping, shreddedParmesan, shreddedProvoloneCheese);

    PizzaSize pizzaSizeLarge = new PizzaSize("large", 16);

    Crust crustOriginal = new Crust();
    crustOriginal.crustName("original").availableSizes(Collections.singletonList(pizzaSizeLarge))
        .price(3.2).dietaryProperties(vegetarian);

    Sauce sauceOriginal = new Sauce();
    sauceOriginal.sauceName("original")
        .dietaryProperties(vegetarian);
    Pizza pizza = new Pizza();
    pizza.pizzaName("test").sauce(sauceOriginal).toppings(toppings).crust(crustOriginal)
        .size(pizzaSizeLarge);

    when(pizzaSizeRepository.findPizzaSizeBySizeDescription("large")).thenReturn(pizzaSizeLarge);
    when(crustRepository.getCrustByCrustName("original")).thenReturn(crustOriginal);
    when(sauceRepository.getSauceBySauceName("original")).thenReturn(sauceOriginal);
    when(toppingRepository.findToppingByToppingName("pepperoni")).thenReturn(pepperoniTopping);
    when(toppingRepository.findToppingByToppingName("shredded provolone cheese"))
        .thenReturn(shreddedProvoloneCheese);
    when(toppingRepository.findToppingByToppingName("shredded parmesan asiago"))
        .thenReturn(shreddedParmesan);

    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("pizzaName", "test")
        .param("toppings", "pepperoni")
        .param("toppings", "shredded provolone cheese")
        .param("toppings", "shredded parmesan asiago")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    Price price = objectMapper.readValue(response.getContentAsString(), Price.class);
    assertEquals((int) (pizza.getPrice() * 100), (int) price.getPriceInCents());
  }

  @Test
  public void getPizzaPrice_InvalidTopping() throws Exception {
    HashMap<DietaryProperty, Boolean> vegetarian = new HashMap<>();
    vegetarian.put(DietaryProperty.VEGAN, false);
    vegetarian.put(DietaryProperty.VEGETARIAN, true);
    vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

    PizzaSize pizzaSizeLarge = new PizzaSize("large", 16);

    Crust crustOriginal = new Crust();
    crustOriginal.crustName("original").availableSizes(Collections.singletonList(pizzaSizeLarge))
        .price(3.2).dietaryProperties(vegetarian);

    Sauce sauceOriginal = new Sauce();
    sauceOriginal.sauceName("original")
        .dietaryProperties(vegetarian);
    when(pizzaSizeRepository.findPizzaSizeBySizeDescription("large")).thenReturn(pizzaSizeLarge);
    when(crustRepository.getCrustByCrustName("original")).thenReturn(crustOriginal);
    when(sauceRepository.getSauceBySauceName("original")).thenReturn(sauceOriginal);
    when(toppingRepository.findToppingByToppingName("pepperoni")).thenReturn(null);

    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("toppings", "pepperoni")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getPizzaPrice_InvalidSauce() throws Exception {
    HashMap<DietaryProperty, Boolean> vegetarian = new HashMap<>();
    vegetarian.put(DietaryProperty.VEGAN, false);
    vegetarian.put(DietaryProperty.VEGETARIAN, true);
    vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

    PizzaSize pizzaSizeLarge = new PizzaSize("large", 16);

    Crust crustOriginal = new Crust();
    crustOriginal.crustName("original").availableSizes(Collections.singletonList(pizzaSizeLarge))
        .price(3.2).dietaryProperties(vegetarian);

    when(pizzaSizeRepository.findPizzaSizeBySizeDescription("large")).thenReturn(pizzaSizeLarge);
    when(crustRepository.getCrustByCrustName("original")).thenReturn(crustOriginal);
    when(sauceRepository.getSauceBySauceName("original")).thenReturn(null);

    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("toppings", "pepperoni")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getPizzaPrice_InvalidCrust() throws Exception {
    PizzaSize pizzaSizeLarge = new PizzaSize("large", 16);

    when(pizzaSizeRepository.findPizzaSizeBySizeDescription("large")).thenReturn(pizzaSizeLarge);
    when(crustRepository.getCrustByCrustName("original")).thenReturn(null);

    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("toppings", "pepperoni")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getPizzaPrice_InvalidSize() throws Exception {
    when(pizzaSizeRepository.findPizzaSizeBySizeDescription("large")).thenReturn(null);

    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("toppings", "pepperoni")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getPizzaPrice_MoreToppings() throws Exception {
    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("toppings", "Test 1")
        .param("toppings", "Test 1")
        .param("toppings", "Test 1")
        .param("toppings", "Test 1")
        .param("toppings", "Test 1")
        .param("toppings", "Test 1")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(400, response.getStatus());
  }

  @Test
  public void createPizza_Exception() throws Exception {
    when(pizzaSizeRepository.findPizzaSizeBySizeDescription("large"))
        .thenThrow(new RuntimeException());

    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("toppings", "pepperoni")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(500, response.getStatus());
  }

  @Test
  public void createPizza_NoHeader() throws Exception {
    MockHttpServletResponse response = this.mockMvc.perform(get("/prices")
        .param("size", "large")
        .param("crustName", "original")
        .param("sauceName", "original")
        .param("toppings", "pepperoni"))
        .andReturn().getResponse();
    assertEquals(501, response.getStatus());


  }
}
