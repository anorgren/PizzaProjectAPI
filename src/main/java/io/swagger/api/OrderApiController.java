package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Item;
import io.swagger.model.Order;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.StoreRepository;
import io.swagger.service.OrderService;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T04:33:40.208Z[GMT]")
@Controller
public class OrderApiController implements OrderApi {
  private final String HEADER_VALUE = "Accept";
  private final String HEADER_CONTENTS = "application/json";

  private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private static final String ORDER_ID_TEXT = "Order_";

  @Autowired
  private OrderRepository repository;

  @Autowired
  private StoreRepository storeRepository;

  @Autowired
  private OrderService orderService;

  @org.springframework.beans.factory.annotation.Autowired
  public OrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Order> createOrder(@ApiParam(value = "list of items with item types to be added order") @Valid @RequestBody List<Item> body,
                                           @ApiParam(value = "Branch Id of the store where order is being placed") @Valid @RequestParam(value = "branchId", required = false) String branchId) {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      try {
        if (storeRepository.findStoreByBranchId(branchId) == null) {
          return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        Order order = new Order();
        order.setOrderId(generateOrderId());
        order.setStoreId(branchId);
        if (body != null && body.size() > 0) {
          order.setItemList(body);
          order.status(Order.StatusEnum.INPROCESS);
        } else {
          order.status(Order.StatusEnum.CREATED);
        }
        order = orderService.updatePrice(order);
        repository.insert(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Error creating order", e);
        return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Order>> getOrders() {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      try {
        List<Order> orders = repository.findAll();
        if (orders == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Error getting orders", e.getMessage());
        return new ResponseEntity<List<Order>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<List<Order>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Order> getOrdersById(@ApiParam(value = "orderId", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      try {
        Order order = repository.findByOrderId(id);
        if (order == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);

      } catch (Exception e) {
        log.error("Error getting orders", e.getMessage());
        return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Order> updateOrder(@ApiParam(value = "orderId", required = true) @PathVariable("id") String id, @ApiParam(value = "") @Valid @RequestBody List<Item> body) {
    String accept = request.getHeader(HEADER_VALUE);
    if (accept != null && accept.contains(HEADER_CONTENTS)) {
      try {
        Order order = repository.findByOrderId(id);
        if (order == null || body == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (Order.StatusEnum.COMPLETED.equals(order.getStatus())) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        order.setItemList(body);
        if(Order.StatusEnum.CREATED.equals(order.getStatus())){
          order.setStatus(Order.StatusEnum.INPROCESS);
        }
        order = orderService.updatePrice(order);
        repository.save(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Error getting orders", e.getMessage());
        return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
  }

  private String generateOrderId() {
    boolean notUnique = true;
    Random rand = new Random();
    String orderId = "";
    while (notUnique) {
      orderId = ORDER_ID_TEXT + String.format("%04d", rand.nextInt(1000000));
      if (repository.findByOrderId(orderId) == null) {
        notUnique = false;
      }
    }
    return orderId;
  }
}
