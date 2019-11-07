package io.swagger.service;

import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppingService {

  @Autowired
  private ToppingRepository toppingRepository;

  public Topping getTopping(String toppingName) {
    return toppingRepository.findToppingByToppingName(toppingName);
  }

  public List<Topping> getAllToppings() {
    return toppingRepository.findAll();
  }
}