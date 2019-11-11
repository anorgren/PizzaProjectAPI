package io.swagger.service.specialUtility;

public interface ApplicableSpecial {

  boolean isApplicable(String orderId);

  void apply(String orderId);
}
