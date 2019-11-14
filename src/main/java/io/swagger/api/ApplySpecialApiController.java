package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Order;
import io.swagger.model.Special;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SpecialsRepository;
import io.swagger.service.OrderService;
import io.swagger.service.specials.ApplicableSpecial;
import io.swagger.service.specials.ApplicableSpecialFactory;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T05:40:35.019Z[GMT]")
@Controller
public class ApplySpecialApiController implements ApplySpecialApi {

  private static final Logger log = LoggerFactory.getLogger(ApplySpecialApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private SpecialsRepository specialsRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ApplicableSpecialFactory applicableSpecialFactory;

  @Autowired
  private OrderService orderService;

  @org.springframework.beans.factory.annotation.Autowired
  public ApplySpecialApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

    public ResponseEntity<Order> applySpecial(@NotNull @ApiParam(value = "specialId", required = true) @Valid @RequestParam(value = "specialId", required = true) String specialId, @NotNull @ApiParam(value = "orderId", required = true) @Valid @RequestParam(value = "orderId", required = true) String orderId) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Special special = specialsRepository.findBySpecialId(specialId);
        if(special == null){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ApplicableSpecial applicableSpecial = applicableSpecialFactory.getApplicableSpecial(special.getSpecialId());
        if(!applicableSpecial.isApplicable(order.getOrderId())){
          return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        applicableSpecial.apply(order.getOrderId());
        order = orderRepository.findByOrderId(orderId);
        order = orderService.updatePrice(order);
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
  }

}
