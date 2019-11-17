package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import io.swagger.model.Dessert;
import io.swagger.model.Item;
import io.swagger.model.Order;
import io.swagger.model.Price;
import io.swagger.model.Soda;
import io.swagger.model.Store;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.StoreRepository;
import io.swagger.service.OrderService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(OrderApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
    {OrderApiController.class, TestContext.class, WebApplicationContext.class})
public class OrderApiControllerTest {

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private StoreRepository storeRepository;

  @MockBean
  private OrderService orderService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  private static final String TEST_BRANCH_ID = "TestBranchId";
  private static final String TEST_ORDER_ID = "TestOrderId";
  private static final String TEST_ORDER_ID_2 = "TestOrderId_2";
  private static final String TEST_DESSERT_NAME = "TestDessertName";
  private static final Integer TEST_PRICE = 100;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testCreateOrderWithoutItemList() throws Exception {
    Store store = new Store().id(TEST_BRANCH_ID);
    when(storeRepository.findStoreByBranchId(TEST_BRANCH_ID)).thenReturn(store);
    when(orderService.updatePrice(any(Order.class))).then(new Answer<Order>() {
      @Override
      public Order answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object[] args = invocationOnMock.getArguments();
        return (Order) args[0];
      }
    });
    MockHttpServletResponse response = this.mockMvc.perform(put("/order")
        .param("branchId", TEST_BRANCH_ID)
        .content("[]")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    Order order = objectMapper.readValue(response.getContentAsString(), Order.class);
    assertEquals(TEST_BRANCH_ID, order.getStoreId());
    assertEquals(Order.StatusEnum.CREATED, order.getStatus());
    assertNotNull(order.getOrderId());
  }

  @Test
  public void testCreateOrderWithItemList() throws Exception {
    Store store = new Store().id(TEST_BRANCH_ID);
    when(storeRepository.findStoreByBranchId(TEST_BRANCH_ID)).thenReturn(store);
    ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
    Dessert dessert = new Dessert().dessertName(TEST_DESSERT_NAME);
    Soda soda = new Soda().size(Soda.SizeEnum.SIX_PACK).sodaName("Coke");
    List<Item> itemList = ImmutableList.of(dessert, soda);
    String itemListJson = objectMapper.writeValueAsString(itemList);
    when(orderService.updatePrice(orderCaptor.capture())).then(new Answer<Order>() {
      @Override
      public Order answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object[] args = invocationOnMock.getArguments();
        Order order = (Order) args[0];
        order.tentativeAmount(new Price().priceInCents(TEST_PRICE));
        return order;
      }
    });
    MockHttpServletResponse response = this.mockMvc.perform(put("/order")
        .param("branchId", TEST_BRANCH_ID)
        .content(itemListJson)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    Order order = objectMapper.readValue(response.getContentAsString(), Order.class);
    assertEquals(TEST_BRANCH_ID, order.getStoreId());
    assertEquals(Order.StatusEnum.INPROCESS, order.getStatus());
    assertEquals(TEST_PRICE, order.getTentativeAmount().getPriceInCents());
    assertEquals(2, order.getItemList().size());
    assertNotNull(order.getOrderId());
  }

  @Test
  public void testCreateOrder_InvalidBranchId() throws Exception {
    when(storeRepository.findStoreByBranchId(TEST_BRANCH_ID)).thenReturn(null);
    MockHttpServletResponse response = this.mockMvc.perform(put("/order")
        .param("branchId", TEST_BRANCH_ID)
        .content("[]")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testCreateOrder_Exception() throws Exception {
    when(storeRepository.findStoreByBranchId(TEST_BRANCH_ID)).thenThrow(new RuntimeException());
    MockHttpServletResponse response = this.mockMvc.perform(put("/order")
        .param("branchId", TEST_BRANCH_ID)
        .content("[]")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(500, response.getStatus());
  }

  @Test
  public void testCreateOrder_NoHeader() throws Exception {
    MockHttpServletResponse response = this.mockMvc.perform(put("/order")
        .param("branchId", TEST_BRANCH_ID)
        .content("[]")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andReturn().getResponse();
    assertEquals(501, response.getStatus());
  }

  @Test
  public void testUpdateOrderWithNoItem() throws Exception {
    Order order = new Order().orderId(TEST_ORDER_ID)
        .status(Order.StatusEnum.CREATED)
        .tentativeAmount(new Price().priceInCents(0));
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    Dessert dessert = new Dessert().dessertName(TEST_DESSERT_NAME);
    Soda soda = new Soda().size(Soda.SizeEnum.SIX_PACK).sodaName("Coke");
    List<Item> itemList = ImmutableList.of(dessert, soda);
    String itemListJson = objectMapper.writeValueAsString(itemList);
    when(orderService.updatePrice(any(Order.class))).then(new Answer<Order>() {
      @Override
      public Order answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object[] args = invocationOnMock.getArguments();
        Order order = (Order) args[0];
        order.tentativeAmount(new Price().priceInCents(TEST_PRICE));
        return order;
      }
    });
    MockHttpServletResponse response = this.mockMvc.perform(put("/order/" + TEST_ORDER_ID)
        .param("branchId", TEST_BRANCH_ID)
        .content(itemListJson)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    Order orderResponse = objectMapper.readValue(response.getContentAsString(), Order.class);
    assertEquals(TEST_ORDER_ID, orderResponse.getOrderId());
    assertEquals(Order.StatusEnum.INPROCESS, orderResponse.getStatus());
    assertEquals(TEST_PRICE, order.getTentativeAmount().getPriceInCents());
    assertEquals(2, order.getItemList().size());
  }

  @Test
  public void testUpdateOrderWithItem() throws Exception {
    Order order = new Order().orderId(TEST_ORDER_ID)
        .status(Order.StatusEnum.CREATED)
        .tentativeAmount(new Price().priceInCents(2000))
        .addItemListItem(new Dessert().dessertName("Choclate Chip Cookie"));
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    Dessert dessert = new Dessert().dessertName(TEST_DESSERT_NAME);
    Soda soda = new Soda().size(Soda.SizeEnum.SIX_PACK).sodaName("Coke");
    List<Item> itemList = ImmutableList.of(dessert, soda);
    String itemListJson = objectMapper.writeValueAsString(itemList);
    when(orderService.updatePrice(any(Order.class))).then(new Answer<Order>() {
      @Override
      public Order answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object[] args = invocationOnMock.getArguments();
        Order order = (Order) args[0];
        order.tentativeAmount(new Price().priceInCents(TEST_PRICE));
        return order;
      }
    });
    MockHttpServletResponse response = this.mockMvc.perform(put("/order/" + TEST_ORDER_ID)
        .param("branchId", TEST_BRANCH_ID)
        .content(itemListJson)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    Order orderResponse = objectMapper.readValue(response.getContentAsString(), Order.class);
    assertEquals(TEST_ORDER_ID, orderResponse.getOrderId());
    assertEquals(Order.StatusEnum.INPROCESS, orderResponse.getStatus());
    assertEquals(TEST_PRICE, order.getTentativeAmount().getPriceInCents());
    assertEquals(2, order.getItemList().size());
  }

  @Test
  public void testUpdateOrderOnCompletedOrder() throws Exception {
    Order order = new Order().orderId(TEST_ORDER_ID)
        .status(Order.StatusEnum.COMPLETED)
        .tentativeAmount(new Price().priceInCents(0));
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    Dessert dessert = new Dessert().dessertName(TEST_DESSERT_NAME);
    Soda soda = new Soda().size(Soda.SizeEnum.SIX_PACK).sodaName("Coke");
    List<Item> itemList = ImmutableList.of(dessert, soda);
    String itemListJson = objectMapper.writeValueAsString(itemList);
    MockHttpServletResponse response = this.mockMvc.perform(put("/order/" + TEST_ORDER_ID)
        .param("branchId", TEST_BRANCH_ID)
        .content(itemListJson)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(400, response.getStatus());
  }

  @Test
  public void testUpdateOrderOnInvalidOrderId() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(null);
    Dessert dessert = new Dessert().dessertName(TEST_DESSERT_NAME);
    Soda soda = new Soda().size(Soda.SizeEnum.SIX_PACK).sodaName("Coke");
    List<Item> itemList = ImmutableList.of(dessert, soda);
    String itemListJson = objectMapper.writeValueAsString(itemList);
    MockHttpServletResponse response = this.mockMvc.perform(put("/order/" + TEST_ORDER_ID)
        .param("branchId", TEST_BRANCH_ID)
        .content(itemListJson)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testUpdateOrderThrowsException() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenThrow(new RuntimeException());
    Dessert dessert = new Dessert().dessertName(TEST_DESSERT_NAME);
    Soda soda = new Soda().size(Soda.SizeEnum.SIX_PACK).sodaName("Coke");
    List<Item> itemList = ImmutableList.of(dessert, soda);
    String itemListJson = objectMapper.writeValueAsString(itemList);
    MockHttpServletResponse response = this.mockMvc.perform(put("/order/" + TEST_ORDER_ID)
        .param("branchId", TEST_BRANCH_ID)
        .content(itemListJson)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(500, response.getStatus());
  }

  @Test
  public void testUpdateOrderNoHeader() throws Exception {
    Dessert dessert = new Dessert().dessertName(TEST_DESSERT_NAME);
    Soda soda = new Soda().size(Soda.SizeEnum.SIX_PACK).sodaName("Coke");
    List<Item> itemList = ImmutableList.of(dessert, soda);
    String itemListJson = objectMapper.writeValueAsString(itemList);
    MockHttpServletResponse response = this.mockMvc.perform(put("/order/" + TEST_ORDER_ID)
        .param("branchId", TEST_BRANCH_ID)
        .content(itemListJson)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andReturn().getResponse();
    assertEquals(501, response.getStatus());
  }

  @Test
  public void testGetOrdersSingleOrder() throws Exception {
    Order order = new Order().orderId(TEST_ORDER_ID);
    when(orderRepository.findAll()).thenReturn(ImmutableList.of(order));
    MockHttpServletResponse response = this.mockMvc.perform(get("/order")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    List<Order> orderList = objectMapper
        .readValue(response.getContentAsString(), new TypeReference<List<Order>>() {
        });
    assertEquals(1, orderList.size());
    assertEquals(TEST_ORDER_ID, orderList.get(0).getOrderId());
  }

  @Test
  public void testGetOrdersMultipleOrder() throws Exception {
    Order testOrder1 = new Order().orderId(TEST_ORDER_ID);
    Order testOrder2 = new Order().orderId(TEST_ORDER_ID_2);
    when(orderRepository.findAll()).thenReturn(ImmutableList.of(testOrder1, testOrder2));
    MockHttpServletResponse response = this.mockMvc.perform(get("/order")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    List<Order> orderList = objectMapper
        .readValue(response.getContentAsString(), new TypeReference<List<Order>>() {
        });
    assertEquals(2, orderList.size());
  }

  @Test
  public void testGetOrdersNoOrderFound() throws Exception {
    when(orderRepository.findAll()).thenReturn(null);
    MockHttpServletResponse response = this.mockMvc.perform(get("/order")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testGetOrdersException() throws Exception {
    when(orderRepository.findAll()).thenThrow(new RuntimeException());
    MockHttpServletResponse response = this.mockMvc.perform(get("/order")
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(500, response.getStatus());
  }

  @Test
  public void testGetOrdersNoHeader() throws Exception {
    MockHttpServletResponse response = this.mockMvc.perform(get("/order"))
        .andReturn().getResponse();
    assertEquals(501, response.getStatus());
  }

  @Test
  public void testGetOrdersById() throws Exception {
    Order order = new Order().orderId(TEST_ORDER_ID);
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    MockHttpServletResponse response = this.mockMvc.perform(get("/order/" + TEST_ORDER_ID)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(200, response.getStatus());
    Order orderResponse = objectMapper.readValue(response.getContentAsString(), Order.class);
    assertEquals(TEST_ORDER_ID, orderResponse.getOrderId());
  }

  @Test
  public void testGetOrdersByIdInvalidId() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(null);
    MockHttpServletResponse response = this.mockMvc.perform(get("/order/" + TEST_ORDER_ID)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testGetOrdersByIdException() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenThrow(new RuntimeException());
    MockHttpServletResponse response = this.mockMvc.perform(get("/order/" + TEST_ORDER_ID)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    assertEquals(500, response.getStatus());
  }

  @Test
  public void testGetOrdersByIdNoHeader() throws Exception {
    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenThrow(new RuntimeException());
    MockHttpServletResponse response = this.mockMvc.perform(get("/order/" + TEST_ORDER_ID))
        .andReturn().getResponse();
    assertEquals(501, response.getStatus());
  }

}