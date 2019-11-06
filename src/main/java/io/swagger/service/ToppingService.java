package io.swagger.service;

import io.swagger.model.Topping;
import java.util.List;

public interface ToppingService {

  Topping getTopping(String toppingName);

  List<Topping> getAllToppings();

}
