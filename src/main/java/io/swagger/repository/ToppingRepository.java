package io.swagger.repository;

import io.swagger.model.Topping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToppingRepository extends MongoRepository<Topping, String> {

}
