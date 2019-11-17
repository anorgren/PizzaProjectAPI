package io.swagger.repository;

import io.swagger.model.Breadstick;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BreadstickRepository extends MongoRepository<Breadstick, String> {

}
