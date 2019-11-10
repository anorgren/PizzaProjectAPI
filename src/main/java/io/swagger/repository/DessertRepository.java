package io.swagger.repository;

import io.swagger.model.Dessert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DessertRepository extends MongoRepository<Dessert, String> {
    Dessert findDessertByDessertName(String desertName);
}
