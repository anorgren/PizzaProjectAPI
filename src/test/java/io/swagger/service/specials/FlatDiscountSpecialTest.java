package io.swagger.service.specials;

import io.swagger.model.Price;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.model.Dessert;
import io.swagger.model.ItemList;
import io.swagger.model.Order;
import io.swagger.model.Soda;
import io.swagger.repository.OrderRepository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class FlatDiscountSpecialTest {

  @Autowired
  private OrderRepository orderRepository;

  private static final String BROWNIE_NAME = "double chocolate chunk brownies";
  private static final String BROWNIE_DESCRIPTION = "Four large gooey double chocolate brownies.";
  private static final Double BROWNIE_PRICE = new Double(4.99);
  private static final String COCA_COLA_PRODUCT_NAME = "coca cola";
  private static final Double TWO_LITER_PRICE = new Double(2.25);

  private static final String ORDER_ONE_ID = "1";
  private static final String ORDER_TWO_ID = "2";

  @Autowired
  private ApplicableSpecialFactory applicableSpecialFactory;

  @Before
  public void setup() {
    orderRepository.deleteAll();
  }

  @After
  public void tearDown() throws Exception {
    orderRepository.deleteAll();
  }

  @Test
  public void isApplicableInvalidOrder() {
    Order firstOrder = new Order();
    firstOrder.setOrderId(ORDER_ONE_ID);
    Soda cokeTwoLiter = new Soda();
    cokeTwoLiter = cokeTwoLiter.sodaName(COCA_COLA_PRODUCT_NAME)
        .size(Soda.SizeEnum.TWO_LITER);
    Dessert brownies = new Dessert();
    brownies.dessertName(BROWNIE_NAME).description(BROWNIE_DESCRIPTION)
        .price(BROWNIE_PRICE);
    ItemList firstOrderItems = new ItemList();
    firstOrderItems.addOrderItemsItem(cokeTwoLiter);
    firstOrderItems.addOrderItemsItem(brownies);
    firstOrder.setItemList(firstOrderItems);
    orderRepository.insert(firstOrder);

    ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial("FlatDiscount");

    assertFalse(special.isApplicable(ORDER_ONE_ID));
  }

  @Test
  public void isApplicableValidOrder() {
    Order secondOrder = new Order();
    secondOrder.setOrderId(ORDER_TWO_ID);
    secondOrder.setItemList(new ItemList());
    //must be more than 5000
    secondOrder.setTentativeAmount(new Price().priceInCents(5500));
    orderRepository.insert(secondOrder);  

    ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial("FlatDiscount");

    assertTrue(special.isApplicable(ORDER_TWO_ID));
  }

  @Test
  public void apply() {
  }
}