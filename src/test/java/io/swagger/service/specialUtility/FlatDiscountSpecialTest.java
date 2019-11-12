package io.swagger.service.specialUtility;

import static org.junit.Assert.*;

import io.swagger.model.Dessert;
import io.swagger.model.ItemList;
import io.swagger.model.Order;
import io.swagger.model.Soda;
import io.swagger.repository.OrderRepository;
import java.math.BigDecimal;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatDiscountSpecialTest {

  @Autowired
  private OrderRepository repository;

  private static final String BROWNIE_NAME = "double chocolate chunk brownies";
  private static final String BROWNIE_DESCRIPTION = "Four large gooey double chocolate brownies.";
  private static final BigDecimal BROWNIE_PRICE = new BigDecimal(4.99);
  private static final String COCA_COLA_PRODUCT_NAME = "coca cola";
  private static final BigDecimal TWO_LITER_PRICE = new BigDecimal(2.25);

  private static final String ORDER_ONE_ID = "1";
  private static final String ORDER_TWO_ID = "2";

  private ApplicableSpecial special;

  @Before
  public void setup() {
    special =  ApplicableSpecialFactory.getApplicableSpecial("FlatDiscount");
    repository.deleteAll();
    Order firstOrder = new Order();
    firstOrder.setOrderId(ORDER_ONE_ID);
    Soda cokeTwoLiter = new Soda();
    cokeTwoLiter = cokeTwoLiter.sodaName(COCA_COLA_PRODUCT_NAME).price(TWO_LITER_PRICE)
        .size(Soda.SizeEnum.TWO_LITER);
    Dessert brownies = new Dessert();
    brownies.dessertName(BROWNIE_NAME).description(BROWNIE_DESCRIPTION)
        .price(BROWNIE_PRICE);
    ItemList firstOrderItems = new ItemList();
    firstOrderItems.addOrderItemsItem(cokeTwoLiter);
    firstOrderItems.addOrderItemsItem(brownies);
    firstOrder.setItemList(firstOrderItems);
    repository.insert(firstOrder);

    Order secondOrder = new Order();
    secondOrder.setOrderId(ORDER_TWO_ID);
    secondOrder.setItemList(new ItemList());
    repository.insert(secondOrder);  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void isApplicableFirstOrder() {
    assertTrue(special.isApplicable(ORDER_ONE_ID));
  }

  @Test
  public void isApplicableSecondOrder() {
    assertTrue(special.isApplicable(ORDER_TWO_ID));
  }

  @Test
  public void apply() {
  }
}