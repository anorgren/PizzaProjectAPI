package io.swagger.repository;

import io.swagger.model.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza, String> {

  Pizza getPizzaByPizzaName(String pizzaName);
}
