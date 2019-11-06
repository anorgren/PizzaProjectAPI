package io.swagger.service;

import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppingServiceImplementation implements ToppingService {

  @Autowired
  private ToppingRepository toppingRepository;

  @SuppressWarnings("finally")
  private Topping findOne(String toppingName) {
    Topping instance = null;
    try {
      List<Topping> toppingList = toppingRepository.findAll();
      for (Topping topping : toppingList) {
        if (topping.getToppingName().equals(toppingName)) {
          instance = topping;
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      return instance;
    }
  }

  @Override
  public Topping getTopping(String toppingName) {
    return this.findOne(toppingName);
  }

  @Override
  public List<Topping> getAllToppings() {
    return toppingRepository.findAll();
  }
}
