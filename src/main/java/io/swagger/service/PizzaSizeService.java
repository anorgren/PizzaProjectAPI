package io.swagger.service;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaSizeService {
  @Autowired
  PizzaSizeRepository pizzaSizeRepository;

  public PizzaSize getPizzaSizeBySizeDescription(String sizeDescription) {
    return pizzaSizeRepository.findPizzaSizeBySizeDescription(sizeDescription.toLowerCase());
  }

  public List<PizzaSize> getAllSizes() {
    return pizzaSizeRepository.findAll();
  }

}
