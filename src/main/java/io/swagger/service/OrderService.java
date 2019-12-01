package io.swagger.service;

import io.swagger.model.Item;
import io.swagger.model.Order;
import io.swagger.model.Price;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    public Order updatePrice(Order order) {
        List<Item> itemList = order.getItemList();
        if (itemList == null || itemList.size() == 0) {
            order.setTentativeAmount(new Price().priceInCents(0));
            return order;
        }
        Price sum = new Price()
                .priceInCents((int) itemList.stream().mapToDouble(Item::getPrice).sum() * 100);
        if (order.getDiscountAmount() != null && order.getDiscountAmount().getPriceInCents() > 0) {
            sum.setPriceInCents(sum.getPriceInCents() - order.getDiscountAmount().getPriceInCents());
        }
        return order.tentativeAmount(sum);
    }
}
