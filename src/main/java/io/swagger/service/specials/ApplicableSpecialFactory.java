package io.swagger.service.specials;

import io.swagger.api.SpecialNotFoundException;
import io.swagger.model.Special;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicableSpecialFactory {
  private final String SPECIAL_TYPE_ONE = "FlatDiscount";
  private final String SPECIAL_TYPE_TWO = "OneFree";
  private final String SPECIAL_TYPE_THREE = "BOGO";

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
  public ApplicableSpecial getApplicableSpecial(String specialId) throws SpecialNotFoundException {
    switch (specialId) {
    case SPECIAL_TYPE_ONE:
      return flatDiscountSpecial;
    case SPECIAL_TYPE_TWO:
      return oneFreeSpecial;
    case SPECIAL_TYPE_THREE:
      return buyOneGetOneFreeSpecial;
    default:
      throw new SpecialNotFoundException(specialId);
    }
  }
}
