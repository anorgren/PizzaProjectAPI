package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

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

import io.swagger.model.Dessert;
import io.swagger.model.Order;
import io.swagger.model.Price;
import io.swagger.model.Special;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SpecialsRepository;
import io.swagger.service.OrderService;
import io.swagger.service.specials.ApplicableSpecial;
import io.swagger.service.specials.ApplicableSpecialFactory;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ApplySpecialApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {ApplySpecialApiController.class, TestContext.class, WebApplicationContext.class})
public class ApplySpecialApiControllerTest {

  @MockBean
  private SpecialsRepository specialsRepository;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private ApplicableSpecialFactory applicableSpecialFactory;

  @MockBean
  private OrderService orderService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testApplicableSpecial() throws Exception {
    Order order = new Order();
    order.orderId("TestOrderId");
    when(orderRepository.findByOrderId("TestOrderId")).thenReturn(order);
    Special special = new Special();
    special.specialId("TestSpecialId");
    when(specialsRepository.findBySpecialId("TestSpecialId")).thenReturn(special);
    MockApplicableSpecial testApplicableSpecial = new MockApplicableSpecial() {
      @Override
      public boolean isApplicable(String orderId) {
        return true;
      }
    };
    when(applicableSpecialFactory.getApplicableSpecial("TestSpecialId")).thenReturn(testApplicableSpecial);
    Dessert dessert = new Dessert().dessertName("testDessert");
    order.addItemListItem(dessert);
    when(orderRepository.findByOrderId("TestOrderId")).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order.tentativeAmount(new Price().priceInCents(1000)));
    String orderJson = objectMapper.writeValueAsString(order);
    this.mockMvc.perform(put("/applySpecial").param("specialId", "TestSpecialId").param("orderId", "TestOrderId")
            .header("Accept", "application/json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(orderJson));
    assertTrue(testApplicableSpecial.isApplied());
  }

  @Test
  public void testNotApplicableSpecial() throws Exception {
    Order order = new Order();
    order.orderId("TestOrderId");
    when(orderRepository.findByOrderId("TestOrderId")).thenReturn(order);
    Special special = new Special();
    special.specialId("TestSpecialId");
    when(specialsRepository.findBySpecialId("TestSpecialId")).thenReturn(special);
    MockApplicableSpecial testApplicableSpecial = new MockApplicableSpecial() {
      @Override
      public boolean isApplicable(String orderId) {
        return false;
      }
    };
    when(applicableSpecialFactory.getApplicableSpecial("TestSpecialId")).thenReturn(testApplicableSpecial);
    this.mockMvc.perform(put("/applySpecial").param("specialId", "TestSpecialId").param("orderId", "TestOrderId")
            .header("Accept", "application/json"))
            .andExpect(status().isNotAcceptable());
    assertFalse(testApplicableSpecial.isApplied());
  }

  @Test
  public void testApplySpecial_InvalidOrderId() throws Exception {
    when(orderRepository.findByOrderId("TestOrderId")).thenReturn(null);
    this.mockMvc.perform(put("/applySpecial").param("specialId", "TestSpecialId").param("orderId", "TestOrderId")
            .header("Accept", "application/json"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testApplySpecial_InvalidSpecialId() throws Exception {
    Order order = new Order();
    order.orderId("TestOrderId");
    when(orderRepository.findByOrderId("TestOrderId")).thenReturn(order);
    when(specialsRepository.findBySpecialId("TestSpecialId")).thenReturn(null);
    this.mockMvc.perform(put("/applySpecial").param("specialId", "TestSpecialId").param("orderId", "TestOrderId")
            .header("Accept", "application/json"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testApplicableSpecial_NoApplicableSpecialFound() throws Exception {
    Order order = new Order();
    order.orderId("TestOrderId");
    when(orderRepository.findByOrderId("TestOrderId")).thenReturn(order);
    Special special = new Special();
    special.specialId("TestSpecialId");
    when(specialsRepository.findBySpecialId("TestSpecialId")).thenReturn(special);
    when(applicableSpecialFactory.getApplicableSpecial("TestSpecialId")).thenThrow(new SpecialNotFoundException("TestSpecialId"));
    this.mockMvc.perform(put("/applySpecial").param("specialId", "TestSpecialId").param("orderId", "TestOrderId")
            .header("Accept", "application/json"))
            .andExpect(status().isInternalServerError());
  }

  @Test
  public void testApplySpecial_NoHeader() throws Exception {
    this.mockMvc.perform(put("/applySpecial").param("specialId", "TestSpecialId").param("orderId", "TestOrderId"))
            .andExpect(status().isNotImplemented());
  }

  private static class MockApplicableSpecial implements ApplicableSpecial {
    public boolean applied = false;

    public boolean isApplied() {
      return applied;
    }

    @Override
    public boolean isApplicable(String orderId) {
      return false;
    }

    @Override
    public void apply(String orderId) {
      applied = true;
    }
  }
}