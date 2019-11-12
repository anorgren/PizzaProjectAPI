package io.swagger.service.specials;

public interface ApplicableSpecial {

  boolean isApplicable(String orderId);

  void apply(String orderId);
}
