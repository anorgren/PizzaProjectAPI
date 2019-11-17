package io.swagger.api;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Order;
import io.swagger.model.PaymentInformation;
import io.swagger.model.Price;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.ReceiptRepository;
import io.swagger.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
@WebMvcTest(CompleteOrderApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
    {CompleteOrderApiController.class, TestContext.class, WebApplicationContext.class})
public class CompleteOrderApiControllerTest {

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private OrderService orderService;

  @MockBean
  private ReceiptRepository receiptRepository;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  private static final String TEST_ORDER_ID = "TestOrderId";

  private static final Integer TENTATIVE_AMOUNT = 100;


  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testCompleteOrder() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");
    ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    when(receiptRepository.findByReceiptId(anyString())).thenReturn(null);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();
    Mockito.verify(orderRepository).save(captor.capture());

    assertEquals(200, response.getStatus());
    Order savedOrder = captor.getValue();
    assertEquals(order.getOrderId(), savedOrder.getOrderId());
    assertEquals(order.getTentativeAmount(), savedOrder.getTentativeAmount());
    assertEquals(Order.StatusEnum.COMPLETED, savedOrder.getStatus());
    assertEquals(paymentInformation, savedOrder.getPayementInformation());
    String receiptResponse = response.getContentAsString();
    assertTrue(receiptResponse.contains("\"orderId\":\"" + TEST_ORDER_ID + "\""));
    assertTrue(
        receiptResponse.contains("\"orderAmount\":{\"priceInCents\":" + TENTATIVE_AMOUNT + "}"));
    assertTrue(receiptResponse.contains("\"receiptId\":\"Receipt_"));
  }

  @Test
  public void testCompleteOrder_InvalidCardNumber_BigNumber() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("1234567891234560123");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidCardNumber_SmallNumber() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidCardNumber_NAN() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123ABC");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidSecurityCode_BigNumber() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("12345");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidSecurityCode_SmallNumber() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("12");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidExpiryDate_ExpiryYear() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2018");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidExpiryDate_ExpiryMonth() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("04/2019");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidExpiryDate_InvalidMonth() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("34/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_InvalidExpiryDate_NAN() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.INPROCESS);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("34/2A20");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    when(orderService.updatePrice(order)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_CompletedOrder() throws Exception {
    Order order = new Order();
    order.orderId(TEST_ORDER_ID);
    order.tentativeAmount(new Price().priceInCents(TENTATIVE_AMOUNT));
    order.status(Order.StatusEnum.COMPLETED);
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");
    ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(order);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(400, response.getStatus());
  }

  @Test
  public void testCompleteOrder_OrderNotFound() throws Exception {
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenReturn(null);
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(404, response.getStatus());
  }

  @Test
  public void testCompleteOrder_ThrowsException() throws Exception {
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");

    when(orderRepository.findByOrderId(TEST_ORDER_ID)).thenThrow(new RuntimeException());
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8)
        .header("Accept", "application/json"))
        .andReturn().getResponse();

    assertEquals(500, response.getStatus());
  }

  @Test
  public void testCompleteOrder_NoHeader() throws Exception {
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.cardNumber("123456789123456");
    paymentInformation.cardSecurityCode("123");
    paymentInformation.cardExpiry("12/2020");
    paymentInformation.nameOnCard("TestCard");
    String paymentInformationJson = objectMapper.writeValueAsString(paymentInformation);

    MockHttpServletResponse response = this.mockMvc.perform(put("/completeOrder")
        .param("id", TEST_ORDER_ID)
        .param("tentativeAmount", Integer.toString(TENTATIVE_AMOUNT))
        .content(paymentInformationJson).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andReturn().getResponse();

    assertEquals(501, response.getStatus());
  }
}