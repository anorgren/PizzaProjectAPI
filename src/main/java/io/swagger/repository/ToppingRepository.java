package io.swagger.repository;

import io.swagger.model.Topping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends MongoRepository<Topping, String> {
  Topping findToppingByToppingName(String toppingName);
}
