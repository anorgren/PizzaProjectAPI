package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Order;
import io.swagger.model.PaymentInformation;
import io.swagger.repository.OrderRepository;
import io.swagger.service.OrderService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:07:33.221Z[GMT]")
@Controller
public class CompleteOrderApiController implements CompleteOrderApi {

  private static final Logger log = LoggerFactory.getLogger(CompleteOrderApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderService orderService;

  @org.springframework.beans.factory.annotation.Autowired
  public CompleteOrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Order> completeOrder(@NotNull @ApiParam(value = "orderId", required = true) @Valid @RequestParam(value = "id", required = true) String id,
                                             @NotNull @ApiParam(value = "tentative amount of order in cents", required = true) @Valid @RequestParam(value = "tentativeAmount", required = true) int tentativeAmount, @ApiParam(value = "Payment Information") @Valid @RequestBody PaymentInformation body) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        Order order = orderRepository.findByOrderId(id);
        if (order == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (Order.StatusEnum.COMPLETED.equals(order.getStatus()) || order.getTentativeAmount().getPriceInCents() != tentativeAmount) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (body == null || !validatePaymentImformation(body)) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        order = orderService.updatePrice(order);
        order.setCalculatedAmount(order.getTentativeAmount());
        order.setPayementInformation(body);
        order.status(Order.StatusEnum.COMPLETED);
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Error Completing order", e);
        return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
  }

  private boolean validatePaymentImformation(PaymentInformation paymentInformation) {
    if (paymentInformation.getCardNumber().length() > 16 || paymentInformation.getCardNumber().length() < 15
            || !isNumeric(paymentInformation.getCardNumber()))
      return false;
    if (paymentInformation.getCardSecurityCode().length() > 4 || paymentInformation.getCardSecurityCode().length() < 3)
      return false;
    int expiryMonth;
    int expiryYear;
    try {
      String[] cardExpiry = paymentInformation.getCardExpiry().split("/");
      expiryMonth = Integer.parseInt(cardExpiry[0]);
      expiryYear = Integer.parseInt(cardExpiry[1]);
    } catch (NumberFormatException e) {
      return false;
    }
    Calendar cal = Calendar.getInstance();
    if (expiryMonth < 1 || expiryMonth > 12)
      return false;
    if (expiryYear < cal.get(Calendar.YEAR))
      return false;
    if (expiryYear == cal.get(Calendar.YEAR)) {
      if (expiryMonth < cal.get(Calendar.MONTH) + 1)
        return false;
    }
    return true;
  }

  private boolean isNumeric(String strNum) {
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  }

}
