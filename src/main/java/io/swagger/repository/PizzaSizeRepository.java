package io.swagger.repository;

import io.swagger.model.PizzaSize;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaSizeRepository extends MongoRepository<PizzaSize, String> {
  //TODO: Create projection that only returns name
  PizzaSize findPizzaSizeBySizeDescription(String sizeDescription);
}
