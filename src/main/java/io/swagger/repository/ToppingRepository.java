package io.swagger.repository;

import io.swagger.model.Topping;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends MongoRepository<Topping, String> {
  interface ToppingName {
    String getToppingName();
  }
  List<ToppingName> findToppingByToppingNameExists(boolean exists);
  Topping findToppingByToppingName(String toppingName);
}
