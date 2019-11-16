package io.swagger.repository;

import io.swagger.model.Topping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToppingRepository extends MongoRepository<Topping, String> {
  interface ToppingName {
    String getToppingName();
  }
  List<ToppingName> findToppingByToppingNameExists(boolean exists);
  Topping findToppingByToppingName(String toppingName);
}
