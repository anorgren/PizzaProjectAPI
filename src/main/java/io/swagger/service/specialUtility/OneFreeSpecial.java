package io.swagger.service.specialUtility;

import io.swagger.model.Item;
import io.swagger.model.Order;
import io.swagger.model.Price;
import io.swagger.repository.OrderRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class OneFreeSpecial implements ApplicableSpecial {

  private static final String SPECIAL_ID = "OneFree";
  private static final BigDecimal MAX_DISCOUNT = new BigDecimal(20.00);
  private static final BigDecimal DOLLARS_TO_CENTS = new BigDecimal(100);

  @Autowired
  private OrderRepository repository;

  /**
   * Returns true if this special can be applied to the given orderId. In this case there must
   * be at laest one item in the order
   * @param orderId orderId of an existing order.
   * @return true if applicable, false if not applicable or orderId doesn't exist.
   */
  @Override
  public boolean isApplicable(String orderId) {
    Order order = repository.findByOrderId(orderId);
    if (order == null || order.getItemList().getOrderItems().size() == 0) {
      return false;
    } else  {
      return true;
    }
  }

  /**
   * Applies the special by updating the specialID and discountAmount of the order. Overwrites
   * previous changes. The cost of the cheapest item will be set to the discount amount. Maximum
   * discount of 20.00.
   * @param orderId the id of an existing order, no change if order doesn't exist.
   */
  @Override
  public void apply(String orderId) {
    Order order = repository.findByOrderId(orderId);
    if (order == null || order.getItemList().getOrderItems().size() == 0 ) {
      return;
    }
    List<Item> items = order.getItemList().getOrderItems();
    BigDecimal discount = MAX_DISCOUNT;
    for (Item toCheck : items) {
      if (discount.compareTo(toCheck.getPrice()) > 0) {
        discount = toCheck.getPrice();
      }
    }
    order.setDiscountAmount(new Price().priceInCents(discount.multiply(DOLLARS_TO_CENTS).intValue()));
    order.setSpecialId(SPECIAL_ID);
  }

}
