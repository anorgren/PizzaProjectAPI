package io.swagger.repository;

import io.swagger.model.Topping;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToppingRepository extends MongoRepository<Topping, String> {
  Topping findToppingByToppingName(String toppingName);
  List<Topping> getAllToppings();

}
