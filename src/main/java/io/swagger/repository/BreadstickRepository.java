package io.swagger.repository;

import io.swagger.model.Breadstick;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BreadstickRepository extends MongoRepository<Breadstick, String> {
    List<Breadstick> getBreadstickByName(String name);
}
