package io.swagger.service;

import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {

  @Autowired
  private ToppingRepository toppingRepository;

  public Topping getTopping(String toppingName) {
    return toppingRepository.findToppingByToppingName(toppingName.toLowerCase());
  }

  public List<Topping> getAllToppings() {
    return toppingRepository.findAll();
  }
}