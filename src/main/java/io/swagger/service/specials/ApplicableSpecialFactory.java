package io.swagger.service.specials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicableSpecialFactory {

  @Autowired
  FlatDiscountSpecial flatDiscountSpecial;
  @Autowired
  OneFreeSpecial oneFreeSpecial;
  @Autowired
  BuyOneGetOneFreeSpecial buyOneGetOneFreeSpecial;

  /**
   * Returns an implementation of the class representing specialId. Returns null
   * if there is no match
   * 
   * @param specialId the given specialId to create
   * @return An object implementing the ApplicableSpecial interface that can be
   *         used to apply a special to an order.
   */
  public ApplicableSpecial getApplicableSpecial(String specialId) {
    // TODO: These are magic strings. Why is this not an enumeration?
    switch (specialId) {
    case "FlatDiscount":
      return flatDiscountSpecial;
    case "OneFree":
      return oneFreeSpecial;
    case "BOGO":
      return buyOneGetOneFreeSpecial;
    default:
      // TODO: This should be a custom exception.
      throw new RuntimeException("Special not implemented: " + specialId);
    }
  }
}
