package io.swagger.service.specials;

import io.swagger.model.Order;
import io.swagger.model.Price;
import io.swagger.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlatDiscountSpecial implements ApplicableSpecial {

    private static final Integer DISCOUNT_AMOUNT = 2000;
    private static final Integer DISCOUNT_MINIMUM = 5000;
    private static final String SPECIAL_ID = "FlatDiscount";

    @Autowired
    private OrderRepository repository;

    /**
     * Returns true if this special can be applied to the given orderId.
     *
     * @param orderId orderId of an existing order.
     * @return true if applicable, false if not applicable or orderId doesn't exist.
     */
    @Override
    public boolean isApplicable(String orderId) {
        Order order = repository.findByOrderId(orderId);
        if (order == null) {
            return false;
        } else if (order.getTentativeAmount() != null) {
            return order.getTentativeAmount().getPriceInCents() > DISCOUNT_MINIMUM;
        } else {
            return false;
        }
    }

    /**
     * Applies the special by updating the specialID and discountAmount of the order. Overwrites
     * previous changes
     *
     * @param orderId the id of an existing order, no change if order doesn't exist.
     */
    @Override
    public void apply(String orderId) {
        if (!isApplicable(orderId)) {
            return;
        }
        Order order = repository.findByOrderId(orderId);
        if (order == null) {
            return;
        }
        order.setDiscountAmount(new Price().priceInCents(DISCOUNT_AMOUNT));
        order.setSpecialId(SPECIAL_ID);
        repository.save(order);
    }
}
