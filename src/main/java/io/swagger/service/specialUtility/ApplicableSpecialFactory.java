package io.swagger.service.specialUtility;

public class ApplicableSpecialFactory {

  /**
   * Returns an implementation of the class representing specialId. Returns null if there is no
   * match
   * @param specialId the given specialId to create
   * @return An object implementing the ApplicableSpecial interface that can be used to apply a
   * special to an order.
   */
  public ApplicableSpecial getApplicableSpecial(String specialId) {
    if (specialId.equals("FlatDiscount")) {
      return new FlatDiscountSpecial();
    } else if (specialId.equals("OneFree")) {
      return new OneFreeSpecial();
    } else if (specialId.equals("BOGO")) {
      return new BuyOneGetOneFreeSpecial();
    } else {
      return null;
    }
  }
}
